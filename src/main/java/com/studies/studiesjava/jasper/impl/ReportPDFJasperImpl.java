package com.studies.studiesjava.jasper.impl;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.jasper.ReportJasper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class ReportPDFJasperImpl implements ReportJasper {
    @Override
    public ReportDTO reportGenerate(ReportTypeEnum type, String path, Map<String, Object> params,
                                    Collection<?> beanCollection, String... joinName) throws JRException {

        InputStream reportStream = getClass().getResourceAsStream(path);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        byte[] printBytes = JasperRunManager.runReportToPdf(jasperReport, params,
                new JRBeanCollectionDataSource(beanCollection));

        return ReportDTO.builder()
                .name(createName(type, joinName))
                .data(printBytes)
                .build();
    }
}
