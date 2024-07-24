import React, { createContext, useState } from 'react';

export const AlumnoContext = createContext();

export const AlumnoProvider = ({ children }) => {
  const [alumnoId, setAlumnoId] = useState(null);
  const [alumnoNombre, setAlumnoNombre] = useState("");

  return (
    <AlumnoContext.Provider value={{ alumnoId, setAlumnoId, alumnoNombre, setAlumnoNombre }}>
      {children}
    </AlumnoContext.Provider>
  );
};
