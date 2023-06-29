package com.studies.studiesjava.jasper;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ReportTypeEnum;
import net.sf.jasperreports.engine.JRException;

import java.util.Collection;
import java.util.Map;

public interface ReportJasper {
    String DOT = ".";

    ReportDTO reportGenerate(ReportTypeEnum type, String path, Map<String, Object> params,
                             Collection<?> beanCollection, String... joinName) throws JRException;

    default String createName(ReportTypeEnum type, String... joinName) {
        return String.join("_", joinName).concat(DOT).concat(type.name()).toLowerCase();
    }
}
