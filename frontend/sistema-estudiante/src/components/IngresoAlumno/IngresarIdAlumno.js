// src/components/IngresoAlumno/IngresarIdAlumno.js
import React, { useContext, useState } from 'react';
import { AlumnoContext } from '../context/AlumnoContext';
import { useNavigate } from 'react-router-dom';
import './IngresarIdAlumno.css';

const IngresarIdAlumno = () => {
  const { setAlumnoId } = useContext(AlumnoContext);
  const [inputValue, setInputValue] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (password === 'password') {
      console.log('Ingresado ID del Alumno:', inputValue);
      setAlumnoId(inputValue);
      navigate('/dashboard-competencias'); // Redirige al dashboard después de establecer el ID
    } else {
      setError('Contraseña incorrecta');
    }
  };

  return (
    <div className='login-container'>
      <h2>Ingresar ID del Alumno</h2>
      <form onSubmit={handleSubmit} className='login-form'>
        <input
          type="number"
          value={inputValue}
          onChange={(e) => setInputValue(e.target.value)}
          placeholder="ID del Alumno"
          required
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Contraseña"
          required
        />
        {error && <p className="error-message">{error}</p>}
        <button type="submit">Enviar</button>
      </form>
    </div>
  );
};

export default IngresarIdAlumno;
