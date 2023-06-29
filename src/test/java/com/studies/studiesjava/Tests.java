package com.studies.studiesjava;

import com.studies.studiesjava.enums.ReportTypeEnum;
import com.studies.studiesjava.service.impl.RelatorioServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Tests {

    @Autowired
    private RelatorioServiceImpl service;

    @Test
    void test() {
        var b = service.gerarRelatorio(ReportTypeEnum.PDF);


    }
}
