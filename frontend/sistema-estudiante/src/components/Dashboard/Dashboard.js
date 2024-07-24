// src/components/Dashboard/Dashboard.js
import React, { useContext, useState } from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import DashboardCompetencias from '../CompetenciasPorCurso/DashboardCompetencias';
import BarChartCompetencias from '../BarChartCompetencias/BarChartCompetencias';
import ProgresoVsPromedioCard from '../ProgresoVsPromedioCard/ProgresoVsPromedioCard';
import ProgresoGlobalCard from '../ProgresoGlobalCard/ProgresoGlobalCard';
import { AlumnoContext } from '../context/AlumnoContext';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
import './Dashboard.css'; // Importa el archivo CSS

const Dashboard = () => {
  const { alumnoId } = useContext(AlumnoContext);
  const periodoAcademicoId = 1; // Puede ser dinámico dependiendo de tu implementación
  const [selectedCursoId, setSelectedCursoId] = useState(null);
  const [selectedCompetenciaId, setSelectedCompetenciaId] = useState(null);

  const downloadPDF = () => {
    const input = document.getElementById('dashboard-content');
    html2canvas(input, { scale: 2, backgroundColor: '#181C29' })
      .then((canvas) => {
        const imgData = canvas.toDataURL('image/png');
        const pdf = new jsPDF('p', 'mm', 'a4');
        const pdfWidth = pdf.internal.pageSize.getWidth();
        const pdfHeight = pdf.internal.pageSize.getHeight();
        const imgWidth = canvas.width;
        const imgHeight = canvas.height;
        const scaleFactor = Math.min(pdfWidth / imgWidth, pdfHeight / imgHeight);

        const imgX = 0;
        const imgY = 0;
        const imgScaledWidth = imgWidth * scaleFactor;
        const imgScaledHeight = imgHeight * scaleFactor;

        pdf.addImage(imgData, 'PNG', imgX, imgY, imgScaledWidth, imgScaledHeight);
        pdf.save('dashboard.pdf');
      })
      .catch((error) => {
        console.error('Error generating PDF:', error);
      });
  };

  return (
    <Container>
      {alumnoId ? (
        <>
          <div id="dashboard-content" style={{ backgroundColor: '#181C29', padding: '20px' }}>
            <DashboardCompetencias onSelectCurso={setSelectedCursoId} />
            {selectedCursoId && selectedCompetenciaId && (
              <Row>
                <Col md={6}>
                  <ProgresoVsPromedioCard 
                    cursoId={selectedCursoId} 
                    competenciaId={selectedCompetenciaId} 
                  />
                </Col>
                <Col md={6}>
                  <ProgresoGlobalCard 
                    cursoId={selectedCursoId} 
                    competenciaId={selectedCompetenciaId} 
                  />
                </Col>
              </Row>
            )}
            <BarChartCompetencias 
              periodoAcademicoId={periodoAcademicoId} 
              onSelectCompetencia={setSelectedCompetenciaId} 
            />
          </div>
          <Button onClick={downloadPDF} className="mt-3 left-button">Descargar PDF</Button>
        </>
      ) : (
        <p>Por favor, ingresa tu ID de alumno.</p>
      )}
    </Container>
  );
};

export default Dashboard;
