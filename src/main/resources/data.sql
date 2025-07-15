INSERT INTO Reserva (id, nome_paciente, nome_medico, data_reserva) VALUES
-- Semana de 21/07/2025
(1, 'Maria Silva', 'João', '2025-07-21 16:00:00'),
(2, 'Pedro Santos', 'Carlos', '2025-07-21 16:00:00'),
(3, 'Ana Oliveira', 'Angelica', '2025-07-21 16:00:00'),
(4, 'Lucas Souza', 'João', '2025-07-21 17:00:00'),
(5, 'Julia Lima', 'Carlos', '2025-07-21 17:00:00'),
(6, 'Gabriel Costa', 'Angelica', '2025-07-21 17:00:00'),
(7, 'Sofia Pereira', 'João', '2025-07-21 18:00:00'),
(8, 'Mateus Rocha', 'Carlos', '2025-07-21 18:00:00'),
(9, 'Isabela Mendes', 'Angelica', '2025-07-21 18:00:00'),

-- Semana de 28/07/2025
(10, 'Thiago Almeida', 'João', '2025-07-28 16:00:00'),
(11, 'Larissa Fernandes', 'Carlos', '2025-07-28 16:00:00'),
(12, 'Daniel Rodrigues', 'Angelica', '2025-07-28 16:00:00'),
(13, 'Beatriz Gomes', 'João', '2025-07-28 17:00:00'),
(14, 'Artur Martins', 'Carlos', '2025-07-28 17:00:00'),
(15, 'Carolina Dias', 'Angelica', '2025-07-28 17:00:00'),
(16, 'Rafael Pires', 'João', '2025-07-28 18:00:00'),
(17, 'Laura Nunes', 'Carlos', '2025-07-28 18:00:00'),
(18, 'Felipe Castro', 'Angelica', '2025-07-28 18:00:00'),

-- Semana de 04/08/2025
(19, 'Mariana Brito', 'João', '2025-08-04 16:00:00'),
(20, 'Guilherme Silva', 'Carlos', '2025-08-04 16:00:00'),
(21, 'Vitória Santos', 'Angelica', '2025-08-04 16:00:00'),
(22, 'Leonardo Oliveira', 'João', '2025-08-04 17:00:00'),
(23, 'Amanda Lima', 'Carlos', '2025-08-04 17:00:00'),
(24, 'Bruno Costa', 'Angelica', '2025-08-04 17:00:00'),
(25, 'Luiza Pereira', 'João', '2025-08-04 18:00:00'),
(26, 'Joana Rocha', 'Carlos', '2025-08-04 18:00:00'),
(27, 'Vinicius Mendes', 'Angelica', '2025-08-04 18:00:00'),

-- Semana de 11/08/2025 (Cobrirá os +30 dias)
(28, 'Helena Alves', 'João', '2025-08-11 16:00:00'),
(29, 'Diego Barbosa', 'Carlos', '2025-08-11 16:00:00'),
(30, 'Julia Dias', 'Angelica', '2025-08-11 16:00:00');

SELECT setval('reserva_id_seq', (SELECT MAX(id) FROM reserva));
