
CREATE TRIGGER message_insert_trigger
AFTER INSERT
ON message
FOR EACH ROW
CALL "com.skillbox.sw.util.MessageInsertTrigger"