-- 1. Adicionar coluna crm_medico na tabela Reserva
ALTER TABLE Reserva ADD COLUMN crm_medico VARCHAR(14);

-- 2. Preencher crm_medico com base no nome parcial (ignorando "Dr./Dra." e usando ILIKE para ser case-insensitive)
UPDATE Reserva r
SET crm_medico = m.crm
FROM Medico m
WHERE m.nome ILIKE CONCAT('%', r.nome_medico, '%');

-- 3. Tornar crm_medico obrigatório
ALTER TABLE Reserva ALTER COLUMN crm_medico SET NOT NULL;

-- 4. Criar a Foreign Key
ALTER TABLE Reserva
ADD CONSTRAINT fk_reserva_medico
FOREIGN KEY (crm_medico)
REFERENCES Medico(crm);

-- 5. Remover a coluna nome_medico
ALTER TABLE Reserva DROP COLUMN nome_medico;
