CREATE TABLE IF NOT EXISTS Medico (
    crm VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(100)
);

INSERT INTO Medico (crm, nome) VALUES
('CRM-SP-123456', 'Dr. João Pedro Silva'),
('CRM-RJ-654321', 'Dra. Angelica Costa'),
('CRM-MG-789123', 'Dr. Carlos Mendes');