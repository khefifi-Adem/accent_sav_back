package com.accent.mahdia.service.serviceImpl;

import com.accent.mahdia.dto.CardStockCountDto;
import com.accent.mahdia.dto.CardsAddDto;
import com.accent.mahdia.dto.CardsDto;
import com.accent.mahdia.entities.Cards;
import com.accent.mahdia.entities.ModelHistory;
import com.accent.mahdia.repository.CardsRepository;
import com.accent.mahdia.repository.ModelHistoryRepository;
import com.accent.mahdia.security.exception.ResourceNotFoundException;
import com.accent.mahdia.service.CardsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardsServiceImpl implements CardsService {

    @Autowired
    CardsRepository cardsRepository;
    @Autowired
    ModelHistoryRepository modelHistoryRepository;

    @Autowired
    protected ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(Cards.class);

    @Override
    public List<Cards> findAll() {
        logger.info("===========================================");
        logger.info("===============Get All Cards===============");
        logger.info("===========================================");
        return cardsRepository.findAll();
    }

    @Override
    public List<Cards> getCardsByIdClient(Integer idClient) {
        logger.info("====================================================");
        logger.info("===============Get Cards By Id Client===============");
        logger.info("====================================================");
        return cardsRepository.getCardsByIdClient(idClient);    }

    @Override
    public List<Cards> add(CardsAddDto cardsAddDto) {
        try {
            List<Cards> cards = cardsAddDto.getCards();

            // Set card model and add date for each card
            LocalDate currentDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(currentDate);
            List<Cards> savedCards = new ArrayList<Cards>();
            for (Cards card: cards) {
                card.setCardModel(cardsAddDto.getCardModel());
                card.setAddDate(date);
                savedCards.add(cardsRepository.save(card));
            }

            ModelHistory modelHistory = new ModelHistory();
            modelHistory.setCardModel(cardsAddDto.getCardModel());
            modelHistory.setTransaction(true);
            modelHistory.setQuantity(savedCards.size());
            modelHistory.setTransactionDate(date);
            modelHistoryRepository.save(modelHistory);
            // Save cards to the repository
            return savedCards;
        } catch (Exception e) {
            // Log the error for troubleshooting
            logger.error("An error occurred while adding cards: " + e.getMessage());
            // You might choose to handle or rethrow the exception depending on your requirements
            throw new RuntimeException("Failed to add cards. Please try again later.");
        }
    }


    @Override
    public List<CardsDto> update(List<CardsDto> cardsDtoList) {
        try {
            // Validate input
            if (cardsDtoList == null || cardsDtoList.isEmpty()) {
                logger.error("CardsDto list is null or empty");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CardsDto list is null or empty");
            }

            // Retrieve all cards to be updated in a batch
            List<Cards> cardsToUpdate = cardsDtoList.stream()
                    .map(cardsDto -> cardsRepository.findById(cardsDto.getId())
                            .orElseThrow(() -> new ResourceNotFoundException("Card with id " + cardsDto.getId() + " not found")))
                    .collect(Collectors.toList());

            // Update cards in batch
            cardsToUpdate.forEach(cards -> {
                // Update card entity with corresponding DTO values
                CardsDto matchingDto = cardsDtoList.stream()
                        .filter(dto -> dto.getId() == cards.getId())
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("No matching DTO found for card id: " + cards.getId()));

                mapper.map(matchingDto, cards);
            });

            // Save and flush changes to the database
            cardsRepository.saveAll(cardsToUpdate);
            cardsRepository.flush();

            // Map updated card entities to DTOs and return
            return cardsToUpdate.stream()
                    .map(cards -> mapper.map(cards, CardsDto.class))
                    .collect(Collectors.toList());

        } catch (ResourceNotFoundException e) {
            logger.error("Failed to update cards. Reason: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Failed to update cards. Reason: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update cards", e);
        }
    }


    @Override
    public Boolean delete(int id) {
        try {
            Cards cardOld = this.cardsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Card with id " + id + " does not exist"));

            this.cardsRepository.deleteById(id);
            logger.info("===============Card Deleted id: {}===============", id);
            return true;
        } catch (ResourceNotFoundException e) {
            // Log and return 404 Not Found status code if card is not found
            logger.error("Failed to delete card with id: {}. Reason: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            // Log any other unexpected exception
            logger.error("Failed to delete card with id: {}. Reason: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete card", e);
        }
    }

    @Override
    public CardsDto getById(Integer id) {
        try {
            Cards cards = this.cardsRepository.findByIdCard(id);
            if (cards == null) {
                logger.error("Card with ID {} does not exist.", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card with ID " + id + " does not exist.");
            } else {
                logger.info("Retrieved card with ID {} successfully.", id);
                return this.mapper.map(cards, CardsDto.class);
            }
        } catch (Exception e) {
            // Log any unexpected exception
            logger.error("Failed to retrieve Card with ID {}: {}", id, e.getMessage());
            // Return 500 Internal Server Error status code
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieve card", e);
        }
    }

    public List<CardStockCountDto> mapStockCountResult(List<Object[]> stockCountResult) {
        List<CardStockCountDto> dtos = new ArrayList<>();
        for (Object[] row : stockCountResult) {
            String model = (String) row[0];
            Integer stock = ((Number) row[1]).intValue(); // Convert to Integer
            dtos.add(new CardStockCountDto(model, stock));
        }
        return dtos;
    }
    @Override
    public List<CardStockCountDto> getStockCount() {
        List<Object[]> stockCountResult = this.cardsRepository.getStockCount();
        List<CardStockCountDto> dtos = mapStockCountResult(stockCountResult);
        return dtos;
    }
}
