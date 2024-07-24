
import com.UNMSM.moduloCompetencias.model.DataWarehousePromedio;
import com.UNMSM.moduloCompetencias.service.DataWarehouseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.UNMSM.moduloCompetencias.controller.DataWarehouseController;

@ExtendWith(MockitoExtension.class)
public class DataWarehouseControllerTest {

    @Mock
    private DataWarehouseService dataWarehouseService;

    @InjectMocks
    private DataWarehouseController dataWarehouseController;

    @Test
    public void testGetCompetenciasPromedioByPeriodoAlumnoCurso() {
        int periodoAcademicoId = 1;
        int alumnoId = 1;
        int cursoId = 1;

        List<DataWarehousePromedio> expectedCompetencias = Arrays.asList(
                new DataWarehousePromedio(1, "Desarrollo ético", 9.5),
                new DataWarehousePromedio(9, "Verifica y validar pruebas de soluciones de software",
                        15.0),
                new DataWarehousePromedio(8, "Desarrolla y mantiene soluciones de software",
                        16.8),
                new DataWarehousePromedio(7, "Gestiona proyectos de desarrollo de software",
                        17.0),
                new DataWarehousePromedio(3, "Capacidad de análisis", 11.7),
                new DataWarehousePromedio(4, "Comunicación oral y escrita", 14.4)
        );

        when(dataWarehouseService.getPromNotasByCompetenciaId(periodoAcademicoId, alumnoId, cursoId))
                .thenReturn(expectedCompetencias);

        List<DataWarehousePromedio> response = dataWarehouseController
                .getCompetenciaProm(periodoAcademicoId, alumnoId, cursoId);

        assertEquals(expectedCompetencias, response);
    }
}
