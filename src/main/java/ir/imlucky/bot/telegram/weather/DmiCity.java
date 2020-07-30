package ir.imlucky.bot.telegram.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DmiCity {
    private int id;

    private double longitude;

    private String label;

    private double latitude;

    private String country_code;

    private String country;
}