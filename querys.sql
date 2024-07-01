USE dbpostulantes;

-- -----------------
-- Login de Usuarios
-- -----------------
SELECT * FROM usuarios;
SELECT * FROM candidatos;
SELECT * FROM reclutadores;

SELECT 
u.rol
FROM usuarios u
WHERE u.username = 'alex.quispe@gmail.com' AND u.pwd = 'candidato2024' AND u.estado = 'activo' LIMIT 1;

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

-- actualizar
UPDATE usuarios SET usuarios.pwd = 'candidato2024v2' WHERE usuarios.id = 1 AND usuarios.username = 'alex.quispe@gmail.com';

-- -------------------
-- ESTUDIOS ACADEMICOS
-- -------------------

SELECT ea.* 
FROM estudios_academicos ea
JOIN candidatos_estudios_academicos cea ON cea.id_estudio_academico = ea.id
WHERE cea.id_candidato = 1;

-- ---------
-- CANDIDATO
-- ---------
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
-- MIS POSTULACIONES
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
WHERE po.id_candidato = 1;
-- WHERE po.estado NOT IN ('cancelado','rechazado','bloqueado');

-- insertar
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado) VALUES (1, 1, 'postulado', NOW());
INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado) VALUES (2, 2, 'postulado', NOW());

-- actualizar
UPDATE postulaciones JOIN empleos ON empleos.id = postulaciones.id_empleo AND empleos.estado = 'activo'
SET postulaciones.estado = 'contratado' WHERE postulaciones.id = 1;

UPDATE postulaciones JOIN empleos ON empleos.id = postulaciones.id_empleo AND empleos.estado = 'disponible'
SET postulaciones.estado = 'contratado' WHERE postulaciones.id = 1;

UPDATE postulaciones SET estado = 'postulado' WHERE id = 1;
UPDATE postulaciones SET estado = 'contactado' WHERE id = 1;
UPDATE postulaciones SET estado = 'entrevistado' WHERE id = 1;
UPDATE postulaciones SET estado = 'contratado' WHERE id = 1;
UPDATE postulaciones SET estado = 'cancelado' WHERE id = 1;
UPDATE postulaciones SET estado = 'rechazado' WHERE id = 1;
UPDATE postulaciones SET estado = 'bloqueado' WHERE id = 1;

-- -----------
-- MIS EMPLEOS
-- -----------
SELECT * FROM postulaciones;
SELECT * FROM candidatos;
SELECT * FROM empleos e WHERE e.id_reclutador = 2;

-- v1
SELECT 
e.id,
e.id_reclutador,
CONCAT(pe.nombre,' ', pe.apellido) AS 'reclutador',
e.titulo, 
e.empresa,
e.sueldo,
e.modalidad,
e.estado,
COUNT(po.id) AS 'total_candidatos_postulados',
e.fecha_creado
FROM empleos e
JOIN reclutadores r ON r.id = e.id_reclutador
JOIN personas pe ON pe.id = r.id_persona 
LEFT JOIN postulaciones po ON po.id_empleo = e.id AND po.estado NOT IN ('cancelado','rechazado','bloqueado')
WHERE e.id_reclutador = 1
GROUP BY 
e.id;

-- v2
SELECT 
e.titulo, 
e.empresa,
e.sueldo,
e.modalidad,
e.estado,
COUNT(po.id) AS 'total_candidatos_postulados',
e.fecha_creado,
e.fecha_actualizado
FROM empleos e
JOIN reclutadores r ON r.id = e.id_reclutador
JOIN personas pe ON pe.id = r.id_persona 
LEFT JOIN postulaciones po ON po.id_empleo = e.id AND po.estado NOT IN ('cancelado','rechazado','bloqueado')
WHERE e.id_reclutador = 1
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
-- Filtro: ID empleo
WHERE e.id = 1;
-- Filtro: candidato que tiene una aptitud en especifica
WHERE e.id = 1 AND LOWER(c.aptitudes) LIKE '%javascript%';
-- Filtro: candidato si tiene experiencia
WHERE e.id = 1 AND cel.id_candidato IS NOT NULL; -- and lower(c.aptitudes) like '%javascript%';
-- Filtro: candidato por su edad
WHERE e.id = 1 AND p.edad >= 18;

-- ------------------------
-- ACTUALIZAR EMPLEO ACTIVO
-- ------------------------
SELECT * FROM empleos;
UPDATE empleos e SET e.estado = 'disponible' WHERE e.id = 1 AND e.estado = 'activo';

-- ---------------------------
-- MOSTRAR EMPLEOS DISPONIBLES
-- ---------------------------
SELECT * FROM empleos;
SELECT * 
FROM empleos e
JOIN postulaciones p ON p.id_empleo = e.id AND e.estado IN ('disponible');

-- ---------------------
-- FINALIZAR PUBLICACION
-- ---------------------
SELECT * FROM empleos;
UPDATE empleos e SET e.estado = 'finalizado' WHERE e.id = 1 AND e.estado = 'disponible';

-- ------------------------------------------------
-- BLOQUEAR CANDIDATO Y MOSTRAR EMPLEOS DISPONIBLES
-- ------------------------------------------------
SELECT * FROM empleos;
SELECT * FROM postulaciones;
SELECT * FROM blacklist;
SELECT * 
FROM empleos e
JOIN postulaciones p ON p.id_empleo = e.id AND e.estado IN ('disponible')
JOIN blacklist b ON b.id_reclutador = e.id_reclutador AND b.id_candidato = p.id_candidato AND b.estado = 'inactivo'; 
INSERT INTO blacklist (id_candidato,id_reclutador) VALUES(1,1);

-- -----------------------------
-- ENVIAR FEEDBACK PERSONALIZADO
-- -----------------------------
SELECT * FROM postulaciones;
UPDATE postulaciones p JOIN empleos e ON e.id = p.id_empleo AND e.estado = 'finalizado'
SET p.feedback = 'Asunto: Resultado del proceso de selección para [nombre del puesto]

Estimado/a [Nombre del Candidato],

Espero que te encuentres bien. Quería agradecerte por tu interés en la posición de [nombre del puesto] en nuestra empresa. Hemos evaluado cuidadosamente todas las candidaturas recibidas y lamentablemente, en esta ocasión, hemos decidido no proceder con tu candidatura.

Quiero enfatizar que tu perfil es valioso y que apreciamos el tiempo y esfuerzo que invertiste en el proceso de selección. Sin embargo, hemos optado por avanzar con otro candidato que se ajusta más a nuestras necesidades actuales.

A continuación, te proporciono algunos comentarios constructivos sobre tu candidatura:

Fortalezas:
[Mencionar aspectos positivos específicos de la candidatura, como habilidades técnicas, experiencia relevante, etc.]
Áreas de mejora:
[Identificar áreas en las que el candidato podría mejorar, como habilidades específicas, experiencia adicional, etc.]
Feedback adicional:
[Cualquier otra observación o consejo que puedas ofrecer al candidato para futuras oportunidades.]
Agradecemos nuevamente tu interés en nuestra empresa y te deseamos mucho éxito en tu búsqueda de empleo. No dudes en mantenernos informados sobre tu carrera profesional, ya que podríamos tener futuras oportunidades que se ajusten a tu perfil.

Atentamente,

[Tu Nombre] [Nombre del Puesto] [Nombre de la Empresa] [Tu Correo Electrónico] [Tu Número de Teléfono]

Recuerda que el feedback constructivo es valioso para el crecimiento profesional de los candidatos. Siempre es importante ser respetuoso y considerado al comunicar una decisión de rechazo. ¡Buena suerte en tus futuras oportunidades!' 
WHERE p.id = 1 AND p.estado = 'entrevistado';

-- -----------------------------
-- ENVIAR FEEDBACK GENERICO
-- -----------------------------
SELECT * FROM postulaciones;
UPDATE postulaciones p JOIN empleos e ON e.id = p.id_empleo AND e.estado = 'finalizado'
SET p.feedback = 'Asunto: Resultado de la selección

Estimado/a [Nombre del Candidato],

Agradecemos tu interés en nuestra empresa y tu participación en el proceso de selección. Lamentablemente, en esta ocasión no avanzaremos con tu candidatura.

Te deseamos mucho éxito en tu búsqueda de empleo y agradecemos nuevamente tu interés.

Atentamente,

[Tu Nombre] [Nombre de la Empresa]

Recuerda que la búsqueda de empleo es un proceso desafiante y que cada oportunidad es una experiencia de aprendizaje. ¡Mucho ánimo en tus futuras aplicaciones!' 
WHERE e.id = 1 AND p.estado = 'postulado';


-- TRANSACCIONES
SHOW FULL PROCESSLIST;
SELECT COUNT(*) FROM information_schema.innodb_trx;
