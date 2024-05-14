USE dbprueba;

-- -----------------
-- Login de Usuarios
-- -----------------

SELECT * FROM usuarios;

SELECT 
u.*,
c.id AS 'id_candidato'
FROM usuarios u
JOIN candidatos c ON c.id_usuario = u.id AND c.estado = 'activo'
WHERE u.username = 'alex.quispe@gmail.com' AND u.pwd = 'candidato2024' AND u.estado = 'activo' LIMIT 1;

SELECT 
u.*,
r.id AS 'id_reclutador'
FROM usuarios u
JOIN reclutadores r ON r.id_usuario = u.id AND r.estado = 'activo'
WHERE u.username = 'maria.gonzales@utp.edu.pe' AND u.pwd = 'reclutador2024' AND u.estado = 'activo' LIMIT 1;

-- -------------------------
-- Información del Candidato
-- -------------------------

SELECT 
c.id, 
c.id_persona, 
p.nombre, 
p.apellido, 
c.imagen_perfil, 
c.path_curriculum_vitae, 
c.path_certificado_trabajo,
c.path_antecendente_policial
FROM candidatos c 
JOIN personas p ON p.id = c.id_persona AND c.estado = 'activo';

SELECT el.* 
FROM experiencias_laborales el 
JOIN candidatos_experiencias_laborales cel ON cel.id_experiencia_laboral = el.id 
WHERE cel.id_candidato = 1;

-- -----------------
-- Mis Postulaciones
-- -----------------

SELECT * FROM empleos;
SELECT * FROM postulaciones;

SELECT 
e.id, 
e.titulo, 
e.empresa,
e.sueldo,
e.modalidad,
po.estado,
CONCAT(pe.nombre, ' ',pe.apellido ) AS 'candidato',
po.fecha_creado
FROM empleos e
JOIN postulaciones po ON po.id_empleo = e.id 
JOIN candidatos c ON c.id = po.id_candidato 
JOIN personas pe ON pe.id = c.id_persona
WHERE po.id_candidato = 2;
-- WHERE po.estado NOT IN ('cancelado','rechazado','bloqueado');

-- insertar
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado) VALUES (1, 1, 'postulado', NOW());
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado) VALUES (2, 2, 'postulado', NOW());

-- actualizar
UPDATE postulaciones SET estado = 'postulado' WHERE id = 1;
UPDATE postulaciones SET estado = 'en_proceso' WHERE id = 1;
UPDATE postulaciones SET estado = 'contratado' WHERE id = 1;
UPDATE postulaciones SET estado = 'cancelado' WHERE id = 1;
UPDATE postulaciones SET estado = 'rechazado' WHERE id = 1;
UPDATE postulaciones SET estado = 'bloqueado' WHERE id = 1;

-- -----------------
-- Mis Publicaciones
-- -----------------
SELECT * FROM postulaciones;
SELECT * FROM candidatos;
SELECT * FROM empleos e WHERE e.id_reclutador = 2;

SELECT 
e.id,
e.id_reclutador,
CONCAT(pe.nombre,' ', pe.apellido) AS 'reclutador',
e.titulo, 
e.empresa,
e.sueldo,
e.modalidad,
COUNT(po.id) AS 'total_candidatos_postulados',
e.fecha_creado
FROM empleos e
JOIN reclutadores r ON r.id = e.id_reclutador
JOIN personas pe ON pe.id = r.id_persona 
LEFT JOIN postulaciones po ON po.id_empleo = e.id AND po.estado NOT IN ('cancelado','rechazado','bloqueado')
WHERE e.id_reclutador = 2
GROUP BY 
e.id;

SELECT 
e.id, 
e.titulo, 
e.empresa,
e.sueldo,
e.modalidad,
po.estado,
c.id AS 'id_candidato',
CONCAT(pe.nombre, ' ',pe.apellido) AS 'candidato',
p.edad,
c.aptitudes,
el.id
FROM empleos e
JOIN postulaciones po ON po.id_empleo = e.id 
JOIN candidatos c ON c.id = po.id_candidato
JOIN personas p ON p.id = c.id_persona
LEFT JOIN candidatos_experiencias_laborales cel ON cel.id_candidato = c.id 
LEFT JOIN experiencias_laborales el ON el.id = cel.id_experiencia_laboral
JOIN personas pe ON pe.id = c.id_persona
-- // Mostrar un empleo y sus candidatos postulados
WHERE e.id = 1;

-- Filtro: candidato que tiene una aptitud en especifica
-- where e.id = 1 and lower(c.aptitudes) like '%javascript%'

-- Filtro: candidato si tiene experiencia
-- where e.id = 1 and cel.id_candidato is not null and lower(c.aptitudes) like '%javascript%';

-- Filtro: candidato por su edad
-- where e.id = 1 and p.edad >= 18;

