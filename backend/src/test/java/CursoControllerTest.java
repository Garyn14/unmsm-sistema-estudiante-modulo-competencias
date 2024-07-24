
import com.UNMSM.moduloCompetencias.ModuloCompetenciasApplication;
import com.UNMSM.moduloCompetencias.model.DTO.CursoDTO;
import com.UNMSM.moduloCompetencias.service.CursoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ModuloCompetenciasApplication.class)
@AutoConfigureMockMvc
public class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @Test
    void testGetCursosByPeriodoIdAlumnoId() throws Exception {
        CursoDTO curso = new CursoDTO(1, "50W0100", "Análisis y Diseño de Software",
                "obligatorio");

        when(cursoService.getCursosByPeriodoAlumno(1, 1))
                .thenReturn(Collections.singletonList(curso));

        mockMvc.perform(get("/api/cursos/cursosByPeriodoAlumno")
                        .param("periodoId", "1")
                        .param("alumnoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" +
                        "{'id':1,'codigo':'50W0100','nombre':'Análisis y Diseño de Software','tipo':'obligatorio'}]"));
    }
}

