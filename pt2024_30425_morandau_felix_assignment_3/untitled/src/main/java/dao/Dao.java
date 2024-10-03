package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Dao class provides generic methods for interacting with a database table.
 *
 * @param <T> the type of objects to be handled by this Dao
 */
public class Dao<T> {
    private Connection connection;
    private Class<T> type;

    /**
     * Constructs a new Dao instance for handling objects of the specified class type.
     *
     * @param clazz the class type of the objects to be handled
     */
    public Dao(Class<T> clazz) {
        this.type = clazz;
    }

    /**
     * Inserts an object into the corresponding database table.
     *
     * @param object the object to be inserted
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    public void insertObject(T object) {
        Field[] fields = type.getDeclaredFields();

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
        queryBuilder.append(type.getSimpleName()).append(" (");

        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                queryBuilder.append(field.getName()).append(", ");
            }
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());
        queryBuilder.append(") VALUES (");

        for (int i = 1; i < fields.length; i++) {
            queryBuilder.append("?, ");
        }
        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length()).append(");");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryBuilder.toString())) {

            int index = 1;
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    field.setAccessible(true);
                    statement.setObject(index++, field.get(object));
                }
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates an object in the corresponding database table.
     *
     * @param updatedObject the updated object
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    public void updateObject(T updatedObject) {
        try {
            Field idField = type.getDeclaredField("id");
            idField.setAccessible(true);
            Object idValue = idField.get(updatedObject);

            StringBuilder queryBuilder = new StringBuilder("UPDATE ");
            queryBuilder.append(type.getSimpleName()).append(" SET ");

            Field[] fields = type.getDeclaredFields();
            List<String> fieldNames = new ArrayList<>();
            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    fieldNames.add(field.getName() + " = ?");
                }
            }

            queryBuilder.append(String.join(", ", fieldNames));
            queryBuilder.append(" WHERE id = ?");

            String query = queryBuilder.toString();

            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                int index = 1;
                for (Field field : fields) {
                    if (!field.getName().equals("id")) {
                        field.setAccessible(true);
                        Object value = field.get(updatedObject);
                        statement.setObject(index++, value);
                    }
                }

                statement.setObject(index, idValue);

                statement.executeUpdate();
            }
        } catch (NoSuchFieldException | IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes an object from the corresponding database table.
     *
     * @param object the object to be deleted
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    public void deleteObject(T object) {
        Field[] fields = type.getDeclaredFields();

        int id = 0;
        for (Field field : fields) {
            if (field.getName().equals("id")) {
                try {
                    field.setAccessible(true);
                    id = (int) field.get(object);
                    break;
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        String query = "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a list of objects from the corresponding database table.
     *
     * @return a list of retrieved objects
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    public List<T> retrieveObjects() {
        List<T> objects = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();

            if (connection != null) {
                String query = "SELECT * FROM " + type.getSimpleName();
                PreparedStatement statement = connection.prepareStatement(query);

                ResultSet resultSet = statement.executeQuery();

                objects = createObjects(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return objects;
    }

    /**
     * Creates a list of objects from the given ResultSet.
     *
     * @param resultSet the ResultSet containing data from the database
     * @return a list of created objects
     * @throws RuntimeException if an SQL exception occurs during execution
     */
    protected List<T> createObjects(ResultSet resultSet) {
        List<T> objects = new ArrayList<>();

        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;

        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }

                objects.add(instance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

        return objects;
    }
}
