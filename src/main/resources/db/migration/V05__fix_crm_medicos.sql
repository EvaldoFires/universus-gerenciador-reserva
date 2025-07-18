
INSERT INTO Medico (crm, nome) VALUES
('12345-6/SP', 'Dr. João Pedro Silva'),
('65432-1/RJ', 'Dra. Angelica Costa'),
('78912-3/MG', 'Dr. Carlos Mendes');

UPDATE Reserva
SET crm_medico = '12345-6/SP'
WHERE crm_medico = 'CRM-SP-123456';

UPDATE Reserva
SET crm_medico = '65432-1/RJ'
WHERE crm_medico = 'CRM-RJ-654321';

UPDATE Reserva
SET crm_medico = '78912-3/MG'
WHERE crm_medico = 'CRM-MG-789123';

DELETE FROM Medico WHERE crm IN (
  'CRM-SP-123456',
  'CRM-RJ-654321',
  'CRM-MG-789123'
);
