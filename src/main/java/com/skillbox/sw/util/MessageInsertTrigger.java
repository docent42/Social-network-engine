package com.skillbox.sw.util;

import org.h2.api.Trigger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageInsertTrigger implements Trigger {
    @Override
    public void init(Connection conn, String schemaName,
                     String triggerName, String tableName, boolean after, int type)
            throws SQLException {}

    @Override
    public void fire(Connection conn, Object[] oldRow, Object[] newRow)
            throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE dialog " +
                        "SET unread_count = (SELECT count(*) FROM message WHERE dialog_id = ? and read_status = 'SENT'), " +
                        "last_message = ? " +
                        "WHERE id = ?")
        ) {
            stmt.setObject(1, newRow[3]);
            stmt.setObject(2, newRow[0]);
            stmt.setObject(3, newRow[3]);

            stmt.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {}

    @Override
    public void remove() throws SQLException {}
}
