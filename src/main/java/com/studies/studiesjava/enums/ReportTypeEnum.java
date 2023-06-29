package com.studies.studiesjava.enums;

import com.studies.studiesjava.jasper.ReportJasper;
import com.studies.studiesjava.jasper.impl.*;

public enum ReportTypeEnum {
    PDF {
        @Override
        public ReportJasper getService() {
            return new ReportPDFJasperImpl();
        }
    },
    CSV {
        @Override
        public ReportJasper getService() {
            return new ReportCSVJasperImpl();
        }
    },
    HTML {
        @Override
        public ReportJasper getService() {
            return new ReportHTMLJasperImpl();
        }
    },
    XML {
        @Override
        public ReportJasper getService() {
            return new ReportXMLJasperImpl();
        }
    },
    PPTX {
        @Override
        public ReportJasper getService() {
            return new ReportPPTXJasperImpl();
        }
    },
    XLSX {
        @Override
        public ReportJasper getService() {
            return new ReportXLSXJasperImpl();
        }
    };

    public abstract ReportJasper getService();
}
