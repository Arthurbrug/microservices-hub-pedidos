INSERT INTO tb_pedido(nome, cpf, data, status, valor_total) VALUES('Jon Snow', '1234567891', '2025-11-25','CRIADO', 790.0 );
INSERT INTO tb_pedido(nome, cpf, data, status, valor_total) VALUES('Jayra Stark', '89101234', '2026-01-25', 'CRIADO', 490.0 );

INSERT INTO tb_item_do_pedido(quantidade, descricao, preco_unitario, pedido_id) VALUES(2, 'Mouse', 250.0, 1);
INSERT INTO tb_item_do_pedido(quantidade, descricao, preco_unitario, pedido_id) VALUES(2, 'Teclado', 240.0, 2);
INSERT INTO tb_item_do_pedido(quantidade, descricao, preco_unitario, pedido_id) VALUES(3, 'tela', 230.0, 3);
