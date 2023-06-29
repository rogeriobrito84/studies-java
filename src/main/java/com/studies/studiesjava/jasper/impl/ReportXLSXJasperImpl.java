package com.studies.studiesjava.jasper.impl;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.jasper.ReportJasper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class ReportXLSXJasperImpl implements ReportJasper {
    @Override
    public ReportDTO reportGenerate(ReportTypeEnum type, String path, Map<String, Object> params,
                                    Collection<?> beanCollection, String... joinName) throws JRException {

        InputStream reportStream = getClass().getResourceAsStream(path);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
                new JRBeanCollectionDataSource(beanCollection));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
        exporter.exportReport();

        byte[] printBytes = outputStream.toByteArray();

        return ReportDTO.builder()
                .name(createName(type, joinName))
                .data(printBytes)
                .build();
    }
}
