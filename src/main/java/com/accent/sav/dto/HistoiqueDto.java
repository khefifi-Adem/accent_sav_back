import java.util.Date;

public class HistoiqueDto {

    private int id;

    private String model;

    private Date dateProd;
    private Date date;

    private int card;
    private Integer card;

    public HistoiqueDto() {
        super();
    }

    public HistoiqueDto(int id, String model, Date dateProd, int card) {
        this.id = id;
        this.model = model;
        this.dateProd = dateProd;
        this.date = date;
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getDateProd() {
        return dateProd;
    }

    public void setDateProd(Date dateProd) {
        this.dateProd = dateProd;
    }

    public int getCard() {
        return card;
    }
    public Date getDate() {
        return date;
    }

    public void setCard(int card) {
        this.card = card;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
