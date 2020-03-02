
create TRIGGER message_INSERT AFTER INSERT
    ON  message 
    FOR EACH ROW 
	    UPDATE sn.dialog
		SET unread_count = (SELECT count(*) FROM message WHERE dialog_id = new.dialog_id and read_status = 'SENT'),
		    last_message = new.id
		WHERE id = new.dialog_id;

	create TRIGGER message_UPDATE AFTER UPDATE
    ON  message 
    FOR EACH ROW 
	    UPDATE sn.dialog
		SET unread_count = (SELECT count(*) FROM message WHERE dialog_id = new.dialog_id and read_status = 'SENT') 
		WHERE id = new.dialog_id;	
	
    