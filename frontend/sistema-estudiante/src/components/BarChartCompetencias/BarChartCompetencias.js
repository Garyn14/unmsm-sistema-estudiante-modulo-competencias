// src/components/BarChartCompetencias/BarChartCompetencias.js
import React, { useEffect, useState, useContext } from 'react';
import { Container, Row, Col, DropdownButton, Dropdown } from 'react-bootstrap';
import { Bar } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { getCompetenciasByPeriodoAlumno, getPromediosByCompetencia } from '../../api/api';
import { AlumnoContext } from '../context/AlumnoContext';
import 'bootstrap/dist/css/bootstrap.min.css';
import './BarChartCompetencias.css';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const BarChartCompetencias = ({ periodoAcademicoId, onSelectCompetencia }) => {
  const { alumnoId } = useContext(AlumnoContext);
  const [competencias, setCompetencias] = useState([]);
  const [selectedCompetencia, setSelectedCompetencia] = useState(null);
  const [promedios, setPromedios] = useState([]);

  const fetchPromedios = async (competenciaId) => {
    try {
      const promediosData = await getPromediosByCompetencia(periodoAcademicoId, alumnoId, competenciaId);
      setPromedios(promediosData);
    } catch (error) {
      console.error('Error fetching promedios:', error);
    }
  };

  useEffect(() => {
    if (!alumnoId) return;

    const fetchCompetencias = async () => {
      try {
        const competenciasData = await getCompetenciasByPeriodoAlumno(periodoAcademicoId, alumnoId);
        setCompetencias(competenciasData);
        if (competenciasData.length > 0) {
          setSelectedCompetencia(competenciasData[0]);
          fetchPromedios(competenciasData[0].id);
          onSelectCompetencia(competenciasData[0].id); // Pasar competenciaId al Dashboard
        }
      } catch (error) {
        console.error('Error fetching competencias:', error);
      }
    };

    fetchCompetencias();
  }, [alumnoId, periodoAcademicoId, onSelectCompetencia]);

  const handleSelect = (competencia) => {
    setSelectedCompetencia(competencia);
    fetchPromedios(competencia.id);
    onSelectCompetencia(competencia.id); // Pasar competenciaId al Dashboard
  };

  const data = {
    labels: promedios.map(promedio => promedio.nombre),
    datasets: [
      {
        label: 'Promedio',
        data: promedios.map(promedio => promedio.promedio),
        backgroundColor: 'rgba(140,170,90,0.7)',
        borderColor: 'rgba(120,150,100,1)',
        borderWidth: 1.5,
      },
    ],
  };

  const options = {
    maintainAspectRatio: false,
    scales: {
      x: {
        ticks: {
          color: 'white'
        },
        grid: {
          color: 'white'
        }
      },
      y: {
        beginAtZero: true,
        max: 20,
        ticks: {
          color: 'white'
        },
        grid: {
          color: '#41434f'
        }
      },
    },
  };

  return (
    <Container className="container">
      {/* <h2>Gráfica de Competencias</h2> */}
      <div className="p-3 my-3 rounded" style={{ backgroundColor: '#41434f' }}>
        <Row className="align-items-center mb-3">
          <Col className="text-left">Semestre actual: <strong>2024 - I</strong></Col>
          <Col className="text-right">
            <div className="dropdown-wrapper">
              <DropdownButton id="dropdown-basic-button" title={selectedCompetencia ? selectedCompetencia.nombre : 'Competencia'}>
                {competencias.map(competencia => (
                  <Dropdown.Item key={competencia.id} onClick={() => handleSelect(competencia)}>
                    {competencia.nombre}
                  </Dropdown.Item>
                ))}
              </DropdownButton>
            </div>
          </Col>
        </Row>
        <Row className="contenedor-figura">
          <Col md={12} lg={8}>
            <div className="chart-container">
              <Bar data={data} options={options} />
            </div>
          </Col>
          <Col md={12} lg={4} className="text-light">
            <section className="info-grafico">
              <h4>Descripción del gráfico</h4>
              <p>
                En esta gráfica se muestra el promedio de cada competencia en los diferentes
                cursos del presente ciclo.
              </p>
              <ol>
                <li>
                  Recuerda que el promedio es en base a las notas subidas
                  hasta la actualidad.
                </li>
                <li>
                  Solo se listan los cursos con competencias en el presente
                  ciclo
                </li>
                <li>
                  Si crees que se trata de un error puedes consultar la sección
                  de calificaciones o consultar directamente al profesor encargado
                  del curso
                </li>
              </ol>
            </section>
          </Col>
        </Row>
      </div>
    </Container>
  );
};

export default BarChartCompetencias;
