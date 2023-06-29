package com.studies.studiesjava.jasper.impl;

import com.studies.studiesjava.api.dto.ReportDTO;
import com.studies.studiesjava.enums.ParamRelTarifaEnum;
import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.jasper.ReportJasper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class ReportHTMLJasperImpl implements ReportJasper {
    @Override
    public ReportDTO reportGenerate(ReportTypeEnum type, String path, Map<String, Object> params,
                                    Collection<?> beanCollection, String... joinName) throws JRException {

        InputStream reportStream = getClass().getResourceAsStream(path);
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,
                new JRBeanCollectionDataSource(beanCollection));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));

        SimpleHtmlExporterConfiguration configuration = new SimpleHtmlExporterConfiguration();
        configuration.setHtmlHeader("");
        configuration.setHtmlFooter("");
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        String html = outputStream.toString().replace("src=\"\"",
                "src=\"" + params.get(ParamRelTarifaEnum.IMAGEM.name()) + "\"");

        byte[] printBytes = html.getBytes();

        return ReportDTO.builder()
                .name(createName(type, joinName))
                .data(printBytes)
                .build();
    }
}
