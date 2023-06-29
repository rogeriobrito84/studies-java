package com.studies.studiesjava.jasper.impl;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.jasper.ReportJasper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class ReportCSVJasperImpl implements ReportJasper {
    @Override
    public ReportDTO reportGenerate(ReportTypeEnum type, String path, Map<String, Object> params,
                                    Collection<?> beanCollection, String... joinName) throws JRException {

        InputStream reportStream = getClass().getResourceAsStream(path);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
                new JRBeanCollectionDataSource(beanCollection));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRCsvExporter exporter = new JRCsvExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
        exporter.exportReport();

        byte[] printBytes = outputStream.toByteArray();

        return ReportDTO.builder()
                .name(createName(type, joinName))
                .data(printBytes)
                .build();
    }
}
