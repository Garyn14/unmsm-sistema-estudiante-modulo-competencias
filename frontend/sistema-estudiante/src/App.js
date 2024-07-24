// src/App.js
import React, { useContext, useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import IngresarIdAlumno from './components/IngresoAlumno/IngresarIdAlumno';
import Dashboard from './components/Dashboard/Dashboard';
import { AlumnoProvider, AlumnoContext } from './components/context/AlumnoContext';
import Header from './components/header/Header';
import { getAlumno } from './api/api';

const App = () => {
  return (
    <AlumnoProvider>
      <AppWithContext />
    </AlumnoProvider>
  );
};

const AppWithContext = () => {
  const { alumnoId, setAlumnoNombre } = useContext(AlumnoContext);
  const [alumnoNombre, setAlumnoNombreState] = useState("");

  useEffect(() => {
    const fetchAlumno = async () => {
      if (alumnoId) {
        try {
          const alumnoData = await getAlumno(alumnoId);
          const nombreCompleto = `${alumnoData.nombres} ${alumnoData.apellidos}`;
          setAlumnoNombre(nombreCompleto);
          setAlumnoNombreState(nombreCompleto);
        } catch (error) {
          console.error('Error fetching alumno data:', error);
        }
      }
    };
    fetchAlumno();
  }, [alumnoId, setAlumnoNombre]);

  return (
    <Router>
      <Header nombre={alumnoNombre} />
      <Routes>
        <Route path="/" element={<IngresarIdAlumno />} />
        <Route path="/dashboard-competencias" element={<Dashboard />} />
      </Routes>
    </Router>
  );
};

export default App;
