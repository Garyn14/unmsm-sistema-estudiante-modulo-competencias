// src/components/ProgresoVsPromedioCard/ProgresoVsPromedioCard.js
import React, { useEffect, useState, useContext } from 'react';
import { Container, ProgressBar } from 'react-bootstrap';
import { AlumnoContext } from '../context/AlumnoContext';
import { getProgresoVsPromedio } from '../../api/api';
import './ProgresoVsPromedioCard.css';

const ProgresoVsPromedioCard = ({ cursoId, competenciaId }) => {
  const { alumnoId } = useContext(AlumnoContext);
  const periodoAcademicoId = 1;
  const [data, setData] = useState(null);

  useEffect(() => {
    if (alumnoId && cursoId && competenciaId) {
      const fetchData = async () => {
        try {
          const response = await getProgresoVsPromedio(periodoAcademicoId, alumnoId, cursoId, competenciaId);
          // Considerar cero si no se encuentran datos
          const result = {
            ...response,
            miProgreso: response.miProgreso || 0,
            progresoSalon: response.progresoSalon || 0
          };
          setData(result);
        } catch (error) {
          console.error('Error fetching progreso vs promedio:', error);
        }
      };
      fetchData();
    }
  }, [alumnoId, cursoId, competenciaId]);

  if (!data) return null;

  return (
    <Container className="progreso-vs-promedio-card">
      <h3>Mi avance Vs el promedio</h3>
      <div className="progress-container">
        <p>Mi Progreso</p>
        <ProgressBar 
          now={data.miProgreso} 
          label={<span style={{ color: 'black' }}>{`${data.miProgreso}`}</span>} 
          variant="success" 
          max={20}
          className='barra-progreso-fondo'
        />
      </div>
      <div className="progress-container">
        <p>Progreso de la clase</p>
        <ProgressBar 
          now={data.progresoSalon} 
          label={<span style={{ color: 'black' }}>{`${data.progresoSalon}`}</span>} 
          variant="" 
          max={20}
          className='barra-progreso-fondo'
        />
      </div>
    </Container>
  );
};

export default ProgresoVsPromedioCard;
