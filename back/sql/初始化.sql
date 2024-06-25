INSERT INTO wms.sys_user (user_name,password,created_time,created_by,update_time,update_by) VALUES
	 ('admin','admin123',NULL,NULL,NULL,NULL);
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
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
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00011','ccc222','su002','0','30'),
	 ('co00012','ccc222','su002','1','15'),
	 ('co00013','bbb777','su002','0','200'),
	 ('co00014','bbb777','su002','1','100'),
	 ('co00015','bbb777','su003','0','200'),
	 ('co00016','bbb777','su003','1','50'),
	 ('co00017','ddd111','su003','0','150'),
	 ('co00018','ddd111','su003','1','100'),
	 ('co00019','ddd111','su003','2','50'),
	 ('co00020','aaa222','su004','0','5');
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00021','aaa222','su004','1','10'),
	 ('co00022','aaa222','su005','0','2'),
	 ('co00023','aaa222','su005','1','4'),
	 ('co00024','aaa333','su004','0','50'),
	 ('co00025','aaa333','su004','1','100'),
	 ('co00026','aaa333','su005','0','25'),
	 ('co00027','aaa333','su005','1','75'),
	 ('co00028','bbb111','su006','0','20'),
	 ('co00029','bbb111','su006','1','10'),
	 ('co00030','bbb111','su007','0','10');
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00031','bbb111','su007','1','5'),
	 ('co00032','bbb222','su006','0','10'),
	 ('co00033','bbb222','su006','1','5'),
	 ('co00034','bbb222','su007','0','5'),
	 ('co00035','bbb222','su007','1','3'),
	 ('co00036','ccc333','su004','0','100'),
	 ('co00037','ccc333','su004','1','50'),
	 ('co00038','ccc333','su005','0','200'),
	 ('co00039','ccc333','su005','1','150'),
	 ('co00040','ddd222','su006','0','10');
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00041','ddd222','su006','1','5'),
	 ('co00042','ddd222','su007','0','20'),
	 ('co00043','ddd222','su007','1','10'),
	 ('co00044','eee111','su008','0','10'),
	 ('co00045','eee111','su008','1','5'),
	 ('co00046','eee111','su009','0','20'),
	 ('co00047','eee111','su009','1','10'),
	 ('co00048','eee222','su008','0','500'),
	 ('co00049','eee222','su008','1','250'),
	 ('co00050','eee222','su009','0','200');
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00051','eee222','su009','1','100'),
	 ('co00052','eee444','su008','0','20'),
	 ('co00053','eee444','su008','1','10'),
	 ('co00054','eee444','su010','0','10'),
	 ('co00055','eee444','su010','1','5'),
	 ('co00056','fff111','su010','0','500'),
	 ('co00057','fff111','su010','1','200'),
	 ('co00058','fff555','su010','0','5'),
	 ('co00059','fff555','su010','1','10'),
	 ('co00060','fff555','su009','0','20');
INSERT INTO wms.wms_container (id,item_no,supplier,container_code,container) VALUES
	 ('co00061','fff555','su009','1','50');
INSERT INTO wms.wms_inbound (id,inbound_no,inbound_status,inbound_time,supplier) VALUES
	 ('ib001','00001',0,'20240405','su001'),
	 ('ib002','00002',0,'20240501','su002'),
	 ('ib003','00003',0,'20240501','su003'),
	 ('R-2024-06-25-095201','',0,'','su001');
INSERT INTO wms.wms_inbound_detail (id,inbound_id,inbound_no,item_no,container,plan_quantity,real_quantity) VALUES
	 ('D-20240625-247212','R-2024-06-25-095201','R-2024-06-25-095201','aaa111',100,200,0),
	 ('ibd00001','ib001','00001','aaa111',100,200,0),
	 ('ibd00002','ib001','00001','aaa111',60,300,0),
	 ('ibd00003','ib001','00001','ccc111',200,400,0),
	 ('ibd00004','ib002','00002','bbb333',20,40,0),
	 ('ibd00005','ib002','00002','ccc222',15,90,0),
	 ('ibd00006','ib002','00002','bbb777',200,600,0),
	 ('ibd00007','ib003','00003','aaa111',50,300,0),
	 ('ibd00008','ib003','00003','bbb777',50,100,0),
	 ('ibd00009','ib003','00003','ddd111',100,100,0);
INSERT INTO wms.wms_inventory (id,item_no,inventory,low,high) VALUES
	 ('iv00001','aaa111',200,NULL,NULL),
	 ('iv00002','bbb333',50,NULL,NULL),
	 ('iv00003','ccc111',300,NULL,NULL),
	 ('iv00004','ccc222',50,NULL,NULL),
	 ('iv00005','bbb777',100,NULL,NULL),
	 ('iv00006','ddd111',250,NULL,NULL),
	 ('iv00007','aaa222',20,NULL,NULL),
	 ('iv00008','aaa333',100,NULL,NULL),
	 ('iv00009','bbb111',40,NULL,NULL),
	 ('iv00010','bbb222',15,NULL,NULL);
INSERT INTO wms.wms_inventory (id,item_no,inventory,low,high) VALUES
	 ('iv00011','ccc333',300,NULL,NULL),
	 ('iv00012','ddd222',40,NULL,NULL),
	 ('iv00013','eee111',30,NULL,NULL),
	 ('iv00014','eee222',500,NULL,NULL),
	 ('iv00015','eee444',40,NULL,NULL),
	 ('iv00016','fff111',200,NULL,NULL),
	 ('iv00017','fff555',30,NULL,NULL);
INSERT INTO wms.wms_item (id,item_no,item_name,supplier) VALUES
	 ('it00001','aaa111','火箭弹','su001'),
	 ('it00002','aaa111','火箭弹','su003'),
	 ('it00003','bbb333','导弹','su002'),
	 ('it00004','ccc111','手雷','su001'),
	 ('it00005','ccc222','核弹头','su001'),
	 ('it00006','ccc222','核弹头','su002'),
	 ('it00007','bbb777','鱼雷','su002'),
	 ('it00008','bbb777','鱼雷','su003'),
	 ('it00009','ddd111','地雷','su003'),
	 ('it00010','aaa222','歼-20','su004');
INSERT INTO wms.wms_item (id,item_no,item_name,supplier) VALUES
	 ('it00011','aaa222','歼-20','su005'),
	 ('it00012','aaa333','喀秋莎火箭炮','su004'),
	 ('it00013','aaa333','喀秋莎火箭炮','su005'),
	 ('it00014','ccc333','AK47','su004'),
	 ('it00015','ccc333','AK47','su005'),
	 ('it00016','bbb111','东风-41弹道导弹','su006'),
	 ('it00017','bbb111','东风-41弹道导弹','su007'),
	 ('it00018','bbb222','055型驱逐舰','su006'),
	 ('it00019','bbb222','055型驱逐舰','su007'),
	 ('it00020','ddd222','052d型驱逐舰','su006');
INSERT INTO wms.wms_item (id,item_no,item_name,supplier) VALUES
	 ('it00021','ddd222','052d型驱逐舰','su007'),
	 ('it00022','eee111','075型两栖攻击舰','su008'),
	 ('it00023','eee111','075型两栖攻击舰','su009'),
	 ('it00024','eee222','67式轻重两用机枪','su008'),
	 ('it00025','eee222','67式轻重两用机枪','su009'),
	 ('it00026','eee444','中国长征6号核潜艇','su008'),
	 ('it00027','eee444','中国长征6号核潜艇','su010'),
	 ('it00028','fff111','QBZ191步枪','su010'),
	 ('it00029','fff555','运-20','su010'),
	 ('it00030','fff555','运-20','su009');
INSERT INTO wms.wms_outbound (id,outbound_no,outbound_status,outbound_time,client) VALUES
	 ('ob001','00001',0,'2024-06-01','华南理工大学'),
	 ('ob002','00002',0,'2024-06-02','中山大学'),
	 ('ob003','00003',0,'2024-06-03','华南师范大学');
INSERT INTO wms.wms_outbound_detail (id,outbound_id,outbound_no,item_no,container,plan_quantity,real_quantity) VALUES
	 ('obd0001','ob001','00001','aaa111',100,100,100),
	 ('obd0002','ob001','00001','bbb111',20,40,40),
	 ('obd0003','ob001','00001','ccc222',30,90,90),
	 ('obd0004','ob002','00002','ddd111',150,300,300),
	 ('obd0005','ob002','00002','bbb777',200,400,400),
	 ('obd0006','ob002','00002','bbb222',5,20,20),
	 ('obd0007','ob003','00003','ccc333',150,300,300),
	 ('obd0008','ob003','00003','fff555',20,40,40),
	 ('obd0009','ob003','00003','eee444',10,30,30);
INSERT INTO wms.wms_supplier (id,supplierCode,supplierName) VALUES
	 ('su001','tx','腾讯'),
	 ('su002','hw','华为'),
	 ('su003','alibaba','阿里巴巴'),
	 ('su004','jingdong','京东'),
	 ('su005','baidu','百度'),
	 ('su006','xiaomi','小米'),
	 ('su007','meituan','美团'),
	 ('su008','zijie','字节跳动'),
	 ('su009','pingduoduo','拼多多'),
	 ('su010','wangyi','网易');
