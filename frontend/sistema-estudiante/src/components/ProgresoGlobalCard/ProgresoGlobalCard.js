
import React, { useEffect, useState, useContext } from 'react';
import { Container } from 'react-bootstrap';
import { AlumnoContext } from '../context/AlumnoContext';
import { getProgresoVsPromedio } from '../../api/api';
import './ProgresoGlobalCard.css';
import ReactSpeedometer from 'react-d3-speedometer';

const ProgresoGlobalCard = ({ cursoId, competenciaId }) => {
  const { alumnoId } = useContext(AlumnoContext);
  const periodoAcademicoId = 1;
  const [data, setData] = useState(null);

  useEffect(() => {
    if (alumnoId && cursoId && competenciaId) {
      const fetchData = async () => {
        try {
          const response = await getProgresoVsPromedio(periodoAcademicoId, alumnoId, cursoId, competenciaId);
          const result = {
            ...response,
            miProgreso: response.miProgreso || 0
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
    <Container className="progreso-global-card">
      <h3>Avance global</h3>
      <ReactSpeedometer
        maxValue={20}
        value={data.miProgreso}
        needleColor="black"
        startColor="red"
        segments={3}
        endColor="green"
        customSegmentStops={[0, 10, 14, 20]}
        segmentColors={['#FF2E2E', '#FFA500', '#188754']}
        textColor="black"
        height={200}
      />
    </Container>
  );
};

export default ProgresoGlobalCard;
