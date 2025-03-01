-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-05-2024 a las 07:48:05
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `asesorias`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `matricula` int(100) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `apellido_paterno` varchar(150) NOT NULL,
  `apellido_materno` varchar(150) NOT NULL,
  `id_programaedu` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`matricula`, `nombre`, `apellido_paterno`, `apellido_materno`, `id_programaedu`) VALUES
(123456789, 'Mario', 'Rossainz', 'Lopez', 2),
(202035891, 'Evelyn', 'Flores', 'Lechuga', 1),
(202045342, 'Itzel', 'Martinez', 'Carrera', 1),
(987654321, 'Valeria', 'Garzon', 'Nava', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscritos`
--

CREATE TABLE `inscritos` (
  `id_inscripcion` int(100) NOT NULL,
  `matricula` int(100) NOT NULL,
  `id_materia` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inscritos`
--

INSERT INTO `inscritos` (`id_inscripcion`, `matricula`, `id_materia`) VALUES
(3, 202045342, 4),
(4, 202045342, 1),
(5, 202035891, 6),
(6, 202035891, 7),
(7, 987654321, 2),
(8, 123456789, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `id_materia` int(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_programaedu` int(100) NOT NULL,
  `id_profesor` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`id_materia`, `nombre`, `id_programaedu`, `id_profesor`) VALUES
(1, 'Modelos de Desarrollo Web', 1, 123456),
(2, 'Modelos de Desarrollo Web', 3, 171819),
(3, 'Modelos de Desarrollo Web', 1, 101010),
(4, 'Arquitectura de Software', 1, 192021),
(5, 'Arquitectura de Software', 2, 242526),
(6, 'Interfaz de Usuario', 1, 282930),
(7, 'Inteligencia de Negocios', 1, 282930);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE `profesores` (
  `id_profesor` int(100) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `apellido_paterno` varchar(150) NOT NULL,
  `apellido_materno` varchar(150) NOT NULL,
  `id_programaedu` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`id_profesor`, `nombre`, `apellido_paterno`, `apellido_materno`, `id_programaedu`) VALUES
(101010, 'Carlos Armando', 'Rios', 'Acevedo', 1),
(123456, 'Mario\r\n', 'Rossainz\r\n', 'Lopez', 1),
(171819, 'Luis Yael', 'Mendez', 'Sanchez', 3),
(192021, 'Steffanie', 'Jimenez ', 'Flores ', 1),
(242526, 'Margarita Carmina', 'Garcia', 'Lopez ', 2),
(282930, 'Gilberto', 'Lopez', 'Poblano ', 1),
(789101, 'Jose Luis', 'Hernandez', 'Ameca', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programa_educativo`
--

CREATE TABLE `programa_educativo` (
  `id_programaedu` int(100) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `programa_educativo`
--

INSERT INTO `programa_educativo` (`id_programaedu`, `nombre`) VALUES
(1, 'ITI'),
(2, 'LCC'),
(3, 'ICC');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitudes`
--

CREATE TABLE `solicitudes` (
  `id_solicitud` int(100) NOT NULL,
  `fecha_asesoria` date NOT NULL,
  `hora_asesoria` time NOT NULL,
  `asunto` varchar(200) DEFAULT NULL,
  `estado` varchar(100) NOT NULL,
  `id_profesor` int(100) NOT NULL,
  `matricula` int(100) NOT NULL,
  `comentario_profesor` varchar(255) DEFAULT NULL,
  `materia` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `solicitudes`
--

INSERT INTO `solicitudes` (`id_solicitud`, `fecha_asesoria`, `hora_asesoria`, `asunto`, `estado`, `id_profesor`, `matricula`, `comentario_profesor`, `materia`) VALUES
(12, '2024-05-06', '05:00:00', 'Aprender a manejar listas ', 'Rechazada', 282930, 202045342, 'Reagenda tu asesoria, para otro dia.', 'Interfaz de Usuario'),
(13, '2024-05-22', '07:00:00', 'Revisar el tema de recursividad', 'Pendiente', 282930, 202045342, NULL, 'Modelos de Desarrollo Web'),
(14, '2024-05-06', '05:05:00', 'Manejar un IDE de java para interfaces', 'Aceptada', 123456, 202035891, 'Perfecto, nos vemos a esa hora en mi cubo.', 'Modelos de Desarrollo Web'),
(15, '2024-05-23', '10:22:00', 'Revisar el programa de cubo de rubik', 'Pendiente', 282930, 202035891, NULL, 'Interfaz de Usuario'),
(16, '2024-05-10', '11:30:00', 'Revision de tesis', 'Pendiente', 192021, 202045342, NULL, 'Arquitectura de Software'),
(17, '2024-05-22', '07:45:00', 'Calificacion final y correccion de calificacion', 'Pendiente', 282930, 202035891, NULL, 'Inteligencia de Negocios'),
(20, '2024-05-27', '12:40:00', 'Errores con el proyecto final.', 'Rechazada', 123456, 987654321, 'A esa hora tengo junta, reagenda para el dia 28.', 'Modelos de Desarrollo Web'),
(21, '2024-06-05', '08:50:00', 'Manejo de servlets con java', 'Pendiente', 123456, 987654321, NULL, 'Modelos de Desarrollo Web');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`matricula`),
  ADD KEY `id_programaedu` (`id_programaedu`) USING BTREE;

--
-- Indices de la tabla `inscritos`
--
ALTER TABLE `inscritos`
  ADD PRIMARY KEY (`id_inscripcion`),
  ADD KEY `matricula` (`matricula`),
  ADD KEY `id_materia` (`id_materia`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`id_materia`),
  ADD KEY `id_programaedu` (`id_programaedu`),
  ADD KEY `id_profesor` (`id_profesor`);

--
-- Indices de la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD PRIMARY KEY (`id_profesor`),
  ADD KEY `id_programaedu` (`id_programaedu`) USING BTREE;

--
-- Indices de la tabla `programa_educativo`
--
ALTER TABLE `programa_educativo`
  ADD PRIMARY KEY (`id_programaedu`);

--
-- Indices de la tabla `solicitudes`
--
ALTER TABLE `solicitudes`
  ADD PRIMARY KEY (`id_solicitud`),
  ADD KEY `id_profesor` (`id_profesor`),
  ADD KEY `matricula` (`matricula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inscritos`
--
ALTER TABLE `inscritos`
  MODIFY `id_inscripcion` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `id_materia` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `programa_educativo`
--
ALTER TABLE `programa_educativo`
  MODIFY `id_programaedu` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `solicitudes`
--
ALTER TABLE `solicitudes`
  MODIFY `id_solicitud` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`id_programaedu`) REFERENCES `programa_educativo` (`id_programaedu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inscritos`
--
ALTER TABLE `inscritos`
  ADD CONSTRAINT `inscritos_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `alumnos` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inscritos_ibfk_2` FOREIGN KEY (`id_materia`) REFERENCES `materias` (`id_materia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `materias`
--
ALTER TABLE `materias`
  ADD CONSTRAINT `materias_ibfk_1` FOREIGN KEY (`id_programaedu`) REFERENCES `programa_educativo` (`id_programaedu`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `materias_ibfk_2` FOREIGN KEY (`id_profesor`) REFERENCES `profesores` (`id_profesor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD CONSTRAINT `profesores_ibfk_1` FOREIGN KEY (`id_programaedu`) REFERENCES `programa_educativo` (`id_programaedu`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitudes`
--
ALTER TABLE `solicitudes`
  ADD CONSTRAINT `solicitudes_ibfk_1` FOREIGN KEY (`id_profesor`) REFERENCES `profesores` (`id_profesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `solicitudes_ibfk_2` FOREIGN KEY (`matricula`) REFERENCES `alumnos` (`matricula`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
