package com.travix.medusa.busyflights.domain.toughjet;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToughJetRequest {
    @NotBlank
    @ApiModelProperty(value = "From", required = true)
    private String from;
    @NotBlank
    @ApiModelProperty(value = "To", required = true)
    private String to;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(value = "Outbound Date", required = true)
    private LocalDate outboundDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty(value = "Inbound Date ", required = true)
    private LocalDate inboundDate;
    @ApiModelProperty(value = "Number of Adults", required = true)
    private int numberOfAdults;
}
