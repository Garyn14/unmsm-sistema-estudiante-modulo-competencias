const apiBaseUrl = "http://localhost:8080/api";

export const getCursos = async (periodoAcademicoId, alumnoId) => {
  const response = await fetch(`${apiBaseUrl}/cursos/cursosByPeriodoAlumno?periodoId=${periodoAcademicoId}&alumnoId=${alumnoId}`);
  return response.json();
};

export const getCompetencias = async (periodoAcademicoId, alumnoId, cursoId) => {
  const response = await fetch(`${apiBaseUrl}/datawarehouse/competencia-promedio?periodoAcademicoId=${periodoAcademicoId}&alumnoId=${alumnoId}&cursoId=${cursoId}`);
  return response.json();
};

export const getAlumno = async (alumnoId) => {
  const response = await fetch(`${apiBaseUrl}/alumnos/${alumnoId}`);
  return response.json();
};

export const getCompetenciasByPeriodoAlumno = async (periodoAcademicoId, alumnoId) => {
  const response = await fetch(`${apiBaseUrl}/competencias/competenciasByPeriodoAlumno?periodoId=${periodoAcademicoId}&alumnoId=${alumnoId}`);
  return response.json();
};

export const getPromediosByCompetencia = async (periodoAcademicoId, alumnoId, competenciaId) => {
  const response = await fetch(`${apiBaseUrl}/datawarehouse/promedio?periodoAcademicoId=${periodoAcademicoId}&alumnoId=${alumnoId}&competenciaId=${competenciaId}`);
  return response.json();
};

export const getProgresoVsPromedio = async (periodoAcademicoId, alumnoId, cursoId, competenciaId) => {
  const response = await fetch(`${apiBaseUrl}/datawarehouse/progreso-vs-salon?periodoId=${periodoAcademicoId}&alumnoId=${alumnoId}&cursoId=${cursoId}&competenciaId=${competenciaId}`);
  return response.json();
};