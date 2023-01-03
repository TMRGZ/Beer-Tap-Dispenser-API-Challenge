INSERT INTO BT_DISPENSER (id, flow_volume) VALUES ('DISPENSER_1', 1.0); -- Recently created dispenser
INSERT INTO BT_DISPENSER (id, flow_volume) VALUES ('DISPENSER_2', 1.0); -- Recently created dispenser
INSERT INTO BT_DISPENSER (id, flow_volume) VALUES ('DISPENSER_3', 1.0); -- Opened dispenser
INSERT INTO BT_DISPENSER (id, flow_volume) VALUES ('DISPENSER_4', 1.0); -- Opened dispenser
INSERT INTO BT_DISPENSER (id, flow_volume) VALUES ('DISPENSER_5', 1.0); -- Closed dispenser

INSERT INTO bt_dispenser_history (id, closed_at, opened_at, total_spent, dispenser_id) values ('D3_HISTORY_1', null, {ts '2023-01-01'}, null, 'DISPENSER_3');
INSERT INTO bt_dispenser_history (id, closed_at, opened_at, total_spent, dispenser_id) values ('D4_HISTORY_1', null, {ts '2023-01-01'}, null, 'DISPENSER_4');
INSERT INTO bt_dispenser_history (id, closed_at, opened_at, total_spent, dispenser_id) values ('D5_HISTORY_1', {ts '2023-01-01'}, {ts '2023-01-01'}, 0.0, 'DISPENSER_5');
