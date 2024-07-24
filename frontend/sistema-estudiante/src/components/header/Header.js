import React from 'react';
import './Header.css';
import '@fortawesome/fontawesome-free/css/all.css';

const Header = ({ nombre }) => {
  return (
    <div className="header">
      <div className="header-left">
        <span>Progreso</span>
      </div>
      <div className="header-center">
        <span>Hola, {nombre || "Usuario"}</span>
      </div>
      <div className="header-right">
        <i className="fas fa-user"></i>
        <i className="fas fa-info-circle"></i>
      </div>
    </div>
  );
};

export default Header;
