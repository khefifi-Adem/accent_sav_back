package com.accent.sav.repository;

import com.accent.sav.entities.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("resetTokenRepository")
public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {

    ResetToken findByresetToken(String resetToken);
}
