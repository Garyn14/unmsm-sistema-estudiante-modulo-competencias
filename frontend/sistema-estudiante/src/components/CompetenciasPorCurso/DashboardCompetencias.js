// src/components/CompetenciasPorCurso/DashboardCompetencias.js
import React, { useEffect, useState, useContext } from 'react';
import { Container, Row, Col, DropdownButton, Dropdown, ProgressBar } from 'react-bootstrap';
import { AlumnoContext } from '../context/AlumnoContext';
import { getCursos, getCompetencias } from '../../api/api';
import './DashboardCompetencias.css';

const DashboardCompetencias = ({ onSelectCurso }) => {
  const { alumnoId } = useContext(AlumnoContext);
  const periodoAcademicoId = 1;

  const [cursos, setCursos] = useState([]);
  const [selectedCurso, setSelectedCurso] = useState(null);
  const [competencias, setCompetencias] = useState([]);

  useEffect(() => {
    if (alumnoId) {
      const fetchData = async () => {
        try {
          const cursosData = await getCursos(periodoAcademicoId, alumnoId);
          setCursos(cursosData);
          if (cursosData.length > 0) {
            setSelectedCurso(cursosData[0]);
            onSelectCurso(cursosData[0].id); // Pasar el cursoId al Dashboard
            const competenciasData = await getCompetencias(periodoAcademicoId, alumnoId, cursosData[0].id);
            setCompetencias(competenciasData);
          }
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      };
      fetchData();
    }
  }, [alumnoId, onSelectCurso]);

  const handleSelect = async (curso) => {
    setSelectedCurso(curso);
    onSelectCurso(curso.id); // Pasar el cursoId al Dashboard
    const competenciasData = await getCompetencias(periodoAcademicoId, alumnoId, curso.id);
    setCompetencias(competenciasData);
  };

  return (
    <Container className="dashboard-competencias-container">
      <h2>Dashboard de competencias</h2>
      <div className="p-3 my-3 rounded" style={{ backgroundColor: '#41434f' }}>
        <Row className="align-items-center mb-3">
          <Col className="text-left">Semestre actual: <strong>2024 - I</strong></Col>
          <Col className="text-right">
            <div className="dropdown-wrapper">
              <DropdownButton id="dropdown-basic-button" title={selectedCurso ? selectedCurso.nombre : 'Curso'}>
                {cursos.map(curso => (
                  <Dropdown.Item key={curso.id} onClick={() => handleSelect(curso)}>
                    {curso.nombre}
                  </Dropdown.Item>
                ))}
              </DropdownButton>
            </div>
          </Col>
        </Row>
        {competencias.map(competencia => (
          <Row key={competencia.id} className="align-items-center competencia-row">
            <Col md={3} className="text-light">
              {competencia.competencia}
            </Col>
            <Col md={9} className="progress-container">
              <ProgressBar 
                now={competencia.promedioNota} 
                label={`${competencia.promedioNota}`} 
                max={20} 
                className={competencia.promedioNota < 10.5 ? 'progress-bar-low' : 'progress-bar-high'} 
              />
            </Col>
          </Row>
        ))}
      </div>
    </Container>
  );
};

export default DashboardCompetencias;

