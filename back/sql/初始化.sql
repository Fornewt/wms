INSERT INTO mydb.sys_user (user_name,password,created_time,created_by,update_time,update_by) VALUES
	 ('admin','admin123',NULL,NULL,NULL,NULL);
INSERT INTO mydb.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00001','aaa111','su001','0','100'),
	 ('co00002','aaa111','su001','1','60'),
	 ('co00003','aaa111','su003','0','80'),
	 ('co00004','aaa111','su003','1','50'),
	 ('co00005','bbb333','su002','0','20'),
	 ('co00006','bbb333','su002','1','10'),
	 ('co00007','ccc111','su001','0','200'),
	 ('co00008','ccc111','su001','1','100'),
	 ('co00009','ccc222','su001','0','20'),
	 ('co00010','ccc222','su001','1','10');
INSERT INTO mydb.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00011','ccc222','su002','0','30'),
	 ('co00012','ccc222','su002','1','15'),
	 ('co00013','bbb777','su002','0','200'),
	 ('co00014','bbb777','su002','1','100'),
	 ('co00015','bbb777','su003','0','200'),
	 ('co00016','bbb777','su003','1','50'),
	 ('co00017','ddd111','su003','0','150'),
	 ('co00018','ddd111','su003','1','100'),
	 ('co00019','ddd111','su003','2','50');
INSERT INTO mydb.wms_inbound (id,inbound_no,inbound_status,inbound_time,supplier) VALUES
	 ('ib001','00001',0,'20240405','su001'),
	 ('ib002','00002',0,'20240501','su002'),
	 ('ib003','00003',0,'20240501','su003');
INSERT INTO mydb.wms_inbound_detail (id,inbound_id,inbound_no,item_no,container,plan_quantity,real_quantity) VALUES
	 ('ibd00001','ib001','00001','aaa111',100,200,0),
	 ('ibd00002','ib001','00001','aaa111',60,300,0),
	 ('ibd00003','ib001','00001','ccc111',200,400,0),
	 ('ibd00004','ib002','00002','bbb333',20,40,0),
	 ('ibd00005','ib002','00002','ccc222',15,90,0),
	 ('ibd00006','ib002','00002','bbb777',200,600,0),
	 ('ibd00007','ib003','00003','aaa111',50,300,0),
	 ('ibd00008','ib003','00003','bbb777',50,100,0),
	 ('ibd00009','ib003','00003','ddd111',100,100,0);
INSERT INTO mydb.wms_inventory (id,item_no,inventory,low,high) VALUES
	 ('iv00001','aaa111',50,NULL,NULL),
	 ('iv00002','bbb333',20,NULL,NULL),
	 ('iv00003','ccc111',10,NULL,NULL),
	 ('iv00004','ccc222',60,NULL,NULL),
	 ('iv00005','bbb777',40,NULL,NULL),
	 ('iv00006','ddd111',20,NULL,NULL);
INSERT INTO mydb.wms_item (id,item_no,item_name,supplier) VALUES
	 ('it00001','aaa111','火箭弹','su001'),
	 ('it00002','aaa111','火箭弹','su003'),
	 ('it00003','bbb333','导弹','su002'),
	 ('it00004','ccc111','手雷','su001'),
	 ('it00005','ccc222','核弹头','su001'),
	 ('it00006','ccc222','核弹头','su002'),
	 ('it00007','bbb777','鱼雷','su002'),
	 ('it00008','bbb777','鱼雷','su003'),
	 ('it00009','ddd111','地雷','su003');
INSERT INTO mydb.wms_supplier (id,supplierCode,supplierName) VALUES
	 ('su001','tx','腾讯'),
	 ('su002','hw','华为'),
	 ('su003','alibaba','阿里巴巴');
