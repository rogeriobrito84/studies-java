package com.studies.studiesjava.api.dto;

import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReportDTO {
    private String name;
    private byte[] data;
}
