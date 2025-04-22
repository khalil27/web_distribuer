package tn.esprit.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;  // ID of the user giving the rating

    @NotNull(message = "Skill owner ID cannot be null")
    private Long skillOwnerId;  // ID of the user whose skill is being rated

    @NotNull(message = "Skill ID cannot be null")
    private Long skillId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer score;

    private String comment;
}