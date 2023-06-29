package com.studies.studiesjava.repository.impl;

import com.studies.studiesjava.MockRelatorio;
import com.studies.studiesjava.repository.RelatorioRepository;
import com.studies.studiesjava.repository.entity.RelatorioEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RelatorioMockRepositoryImpl implements RelatorioRepository {
    @Override
    public RelatorioEntity getRelatorio() {
        return MockRelatorio.gerarRelatorio();
    }
}
