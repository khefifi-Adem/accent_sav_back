package com.accent.mahdia.repository;

import com.accent.mahdia.entities.ConfirmationToken;
import com.accent.mahdia.entities.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("resetTokenRepository")
public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {

    ResetToken findByresetToken(String resetToken);
}
