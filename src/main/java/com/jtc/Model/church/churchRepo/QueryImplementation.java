

package com.jtc.Model.church.churchRepo;

import com.jtc.Model.church.churchEntity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Component

public class QueryImplementation extends AbstractTableModel {



    @Autowired
    private  JdbcTemplate jdbcTemplate;// = new JdbcTemplate() ;
    // @Autowired
  SqlRowSetMetaData resultSetMetaData;
  ResultSetMetaData resultSetMetaData1;
   SqlRowSet resultSet;
    private int numberOfRows;

    public QueryImplementation( )  {
    }

 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



   /* @Bean
    public  RowSetMetaData resultSetMetaData()
    {
        return resultSetMetaData ;
    }*/

    public void setQuery(SqlRowSet query) throws SQLException, IllegalStateException {

        resultSetMetaData =  query.getMetaData();
        query.last();
        resultSet = query;
        numberOfRows = query.getRow();
        fireTableStructureChanged();
    }

    public String getColumnName(int column) {
        /*if (!status) {
            throw new IllegalStateException("Not connected to DataBase");
        } else {*/
        //    try {
                return resultSetMetaData.getColumnName(column + 1);
            /*} catch (SQLException sql) {
                sql.printStackTrace();
                return "";
            }*/
     //   }
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        /*if (!status) {
            throw new IllegalStateException("Not connected to DataBase");
        } else {*/
            try {
                String className = resultSetMetaData.getColumnClassName(column + 1);
                return Class.forName(className);
            }/* catch (SQLException sql) {
                sql.printStackTrace();
            }*/ catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }

            return Object.class;
      //  }
    }

    public int getColumnCount() throws IllegalStateException {
        int value = 0;
        /*if (!status) {
            throw new IllegalStateException("Not connected to Database");
        } else {*/
     //   try {
        if (resultSetMetaData==null)
        {

        }
        else
            value = resultSetMetaData.getColumnCount();
        /*} catch (SQLException sql) {
            sql.printStackTrace();
            return 0;
        }*/
        //}
        return value;
    }

    public int getRowCount() throws IllegalStateException {
        /*if (!status) {
            throw new IllegalStateException("Not connected to Database");
        } else {*/
            return numberOfRows;
       // }
    }

    public Object getValueAt(int row, int column) throws IllegalStateException
    {
        /*if (!status) {
            throw new IllegalStateException("Not connected to Database");
        } else {*/
          //  try {
     //   try {
            resultSet.absolute(row + 1);
       /* } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {*/
            return resultSet.getObject(column + 1);
        /*} catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
            /*} catch (SQLException sql) {
                sql.printStackTrace();
                return "";
            }*/
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/*

    @Query("SELECT s FROM Member s WHERE s.firstName = ?1 ")
    Optional<Member> getIdFromMember(String firstname);
*/


    public int deleteNonMember(String id)
    {
        String sql = " Delete from Non_Worker where non_Worker_Id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int deleteWorker(String id)
    {
        String sql = " Delete from workers where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int deleteMember(String id)
    {
        String sql = " Delete from Member WHERE id = ?";
        deleteNonMember(id);
        deleteWorker(id);
        return jdbcTemplate.update(sql,id);
    }


    public void getByIdFromMember(String idNumber)
    {
        String sql = "SELECT * FROM Member  WHERE id = ? ";
        try {
            setQuery(jdbcTemplate.queryForRowSet(sql, idNumber));
        }
        catch(SQLException sqlE)
        {
            sqlE.printStackTrace();
        }
    }


    public int addWorker(Workers workers)
    {
        String sql = "INSERT INTO workers (worker_Id,worker_position,join_date,depart_names) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, workers.getWorkerId(),
                workers.getWorkerPosition(),workers.getJoinDate(),workers.getDepartNames());

    }

    public void getAllWorkers()
    {
        String sql = " SELECT member.id, member.sex, member.last_name, member.first_name, member.marry ,member.address , member.phone ," +
                " member.email_address,member.attendance ,member.login_date,member.in_church,member.resent,workers.depart_names," +
                "workers.worker_position " +
                "FROM member " +
                "INNER JOIN Workers WHERE workers.worker_Id = member.id";
        // ,
        try {
                setQuery(jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public int addNonWorkers(NonWorker nonWorker)
    {
        String sql =" INSERT INTO non_worker (non_Worker_Id,join_date)VALUES(?,?)";
        return jdbcTemplate.update(sql, nonWorker.getNonWorkerId(),nonWorker.getJoinDate());
    }
    public void getAllNonWorker()
    {
        String sql = "SELECT id,sex,last_name,first_name,marry,address,phone," +
                "  email_address,attendance ,login_date,in_church,resent FROM member" +
                "  INNER JOIN Non_Worker WHERE Non_Worker.non_worker_Id =  Member.id ";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    public int addMaintenanceActivity(Maintenance maint)
    {
        String sql = " INSERT INTO maintenance (work_number,work_name,description,activity_start_date," +
                "completion_date,cost,supervisor_Id,status,remark)VALUES(?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,maint.getWorkNumber(),maint.getWork_name(),maint.getDescription(),maint.getActivityStartDate(),
                maint.getCompletionDate(),maint.getCost(),maint.getSupervisorId(),maint.getStatus(),maint.getRemark());

    }
    public void getAllMaintenanceInfo()
    {
        String sql = " SELECT * FROM maintenance";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

    }

/*Optional<Member>findByFirstNameAndLastName(String first,String last);*/
    public void findByFirstNameAndLastName(String first, String last)  {
        Object[] objects = new Object[2];
        objects[0] = first;
        objects[1] = last;
        String sql = "SELECT * FROM member  WHERE member.first_name = ? && member.last_name = ? ";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,first,last));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
     //   return jdbcTemplate.queryForObject(sql,Member.class);

    }
/* Optional<Member>findByFirstName(String first);*//*
    public  Member findByFirstName(String first)
    {
        String sql = "SELECT s FROM Member s WHERE s.first_name = ?";
        return jdbcTemplate.queryForObject(sql, Member.class);
    }*/

    public  void findByFirstName(String first)
    {
        String sql = "SELECT * FROM Member  WHERE first_name = ?";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,first));
        }
        catch(EmptyResultDataAccessException e)
        {
            return;
        }
        catch (NullPointerException n)
        {
            return;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

/*
    @Query("SELECT m FROM Member m")
    List<Member> findNameAndDateBorn();*/



//    public List<BirthAndName> findNameAndBateBorn()
        public void findNameAndDateBorn()
    {
        String sql = " SELECT first_name, last_name,date_born FROM member";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    //  @Query("UPDATE Member SET currentDate = ? WHERE id = ?")
    //  void addCurrentDate();

  //  @Query("SELECT m From Member m where m.id = ?1")
  //  Optional<Member>displayName(String memberId);
    //public Optional<Member> displayName(String memberId)
    public void displayName(String memberId)
    {
        String sql = "SELECT m From member m where m.id = ?";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,memberId));
        }
        catch(EmptyResultDataAccessException ex)
        {
            return;
        }
        catch (NullPointerException n)
        {
            return;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        /*
        return jdbcTemplate.query(sql,(rs, rowNum) -> new Member(rs.getString("id" ),
                rs.getString("sex"),rs.getString("last_name"),
                rs.getString("first_name"), rs.getNString("marry"), rs.getString("address"),
                rs.getString("phone"), rs.getString("date_born"),rs.getString("email_address"),
                rs.getInt("attendance"), rs.getString("logindate"), rs.getString("inchurch"),
                rs.getString("resent")),memberId).stream().findFirst();*/
    }

    


/*
     * Use the naitive Query to update the member table. the inchurch
     * property will be update to reflect the present day. to show if the user was in church the last time
     * *//*


    */
/*@Modifying
    @Query(value = "update member SET in_church = ?1 WHERE id = ?2", nativeQuery = true)
    public int updateMemberInChurch(String present, String memberId);
    */

    public int updateMemberInChurch(String memberId)
    {
        String sql = "update member SET in_church = 'PRESENT' WHERE id = ?";
        /*try {
            setQuery(jdbcTemplate.queryForRowSet(sql,memberId));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }*/
        return jdbcTemplate.update(sql, memberId);
    }

/*    @Modifying
    @Query(value =" update member set attendance = ?1 where id = ?2" , nativeQuery = true)
    int updateAttendanceOfMember( int attendance,String idMember);*/

    public int updateAttendanceOfMembers( String id)
    {
        String sql = " update member set in_church = 'PRESENT' where id = ?";
        return jdbcTemplate.update(sql,id);
    }

    public int addChurchAttendance(ServiceAttendance service)
    {
        String sql = " INSERT INTO service_attendance ( service_date,minist,attendance_number,church_service,attendance_female," +
                "attendance_male, attendance_children)VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,service.getServiceDate(),service.getMinist(),service.getAttendanceNumber(),service.getChurch_service(),
                service.getAttendanceFemale(),service.getAttendanceMale(),service.getAttendanceChildren());
    }

    public void findAllAttendance()
    {
        String sql = "SELECT * FROM service_attendance";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

/*
* This method give a detail of all the activities in the church, fron the table.
* */
    public void findAllAttendanceByDate(String dateService)
    {
        String sql = "SELECT * FROM service_attendance WHERE service_date = ?";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,dateService));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public int addDepartment(Department department)
    {
        String sql = " INSERT INTO department (depart_name,depart_total,depart_head)" +
                "VALUES(?,?,?)";
        return jdbcTemplate.update(sql, department.getDepartName(),department.getTotalMember(),department.getHeadName());
    }

    public void findAllDepartment()
    {
        String sql = "SELECT * FROM department";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

    }

    public NonWorker findNonWorkerById(String nonWorkerId)
    {
        String sql = "Select * from non_worker";
        try {
            return jdbcTemplate.query(sql, (rs, numRow) -> new NonWorker(rs.getString("non_Worker_Id"),
                    rs.getString("join_date"))).stream().findFirst().orElseThrow();
        }
        catch (NoSuchElementException ex)
        {
            return new NonWorker("NCMP000","00-00-00");
        }
    }


/*
     *
     * method to update the current date in the data base for each member
     * *//*
*/
/*


    @Modifying
    @Query(value = "update member set resent = ?1 where id = ?2 ",nativeQuery = true)
    int updateCurrentDateInfo(String resent, String idForUser);
    */


    public void getBirthDaysWorker()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String formatedDate = dateFormat.format(new Date());
        String searchString = formatedDate.substring(0,5);
        String sql = "SELECT last_name,first_name, marry, address,phone,date_born FROM member " +
                "INNER JOIN workers WHERE workers.worker_Id = member.id AND member.date_born = ? ";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,searchString));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

    }
    public List<Member> getBirthDaysWorkerList()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String formatedDate = dateFormat.format(new Date());
        String searchString = formatedDate.substring(3,5);
        String sql = "SELECT last_name,first_name, marry, address,phone,date_born FROM member " +
                "INNER JOIN workers WHERE workers.worker_Id = member.id AND member.date_born = ? ";

        List<Member> memberList= jdbcTemplate.query(sql,(rs, rowNum) -> new Member(rs.getString("id" ),
                rs.getString("sex"),rs.getString("last_name"),
                rs.getString("first_name"), rs.getNString("marry"), rs.getString("address"),
                rs.getString("phone"), rs.getString("date_born"),rs.getString("email_address"),
                rs.getInt("attendance"), rs.getString("logindate"), rs.getString("inchurch"),
                rs.getString("resent")),searchString);

                return memberList.stream().filter(member ->
                (member.getDateBorn().substring(3,5).equalsIgnoreCase(searchString))).toList();

    }

/*
* This method will be called once a member login. The parameters are the  new date and the id login
* returns an integer to signifys success.
* */
    public int updateCurrentDateInfo(String member, String id)
    {
        Object[] object = new Object [2];
        object[0] = member;
        object[1] = id;
        String sql = "update member set resent = ? where id = ? ";
        return jdbcTemplate.update(sql,member,id);
    }

/*
     * method to select those that are not present in church
     * *//*
*/

/*
    @Query("SELECT m from Member m where m.inChurch = ?1")
    List<Member>selectInOrOutSunday(String presentOrNot);*/

    public void selectInOrOutSunday(String presentOrNot)
    {
        String sql = "SELECT * from Member  where in_church = ?";
        String value = presentOrNot.toUpperCase();
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,value));
        }
        catch (NullPointerException ex)
        {
            return;
        }
        catch (EmptyResultDataAccessException ex)
        {
            return ;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public boolean findByIdValue(String id)
    {
        String sql = "SELECT id FROM member WHERE member.id = ?";
        try {
            String result =(jdbcTemplate.queryForObject(sql, (rs, numRow) ->
                    new String(rs.getString("id")), id));
            System.out.println("result");
            System.out.println(result);
            if(result.equalsIgnoreCase(id)) {
                return true;
            }
            else
                return false;
        }
        catch (NullPointerException n)
        {
            return false;
        }
        catch(EmptyResultDataAccessException ex)
        {
            return false;
        }

    }

    public String searchAttendanceById(String id)
    {
        String value = "";
        if(!id.equalsIgnoreCase("")|| id!=null)
        {
            String sql = "Select attendance from member where id = ?";
            try {
                value = jdbcTemplate.queryForObject(sql, (rs, numRow) ->
                        new String(rs.getString("id")),id);
            } catch (EmptyResultDataAccessException ex) {
                return value;
            }
        }
        return value;

    }
    public Member displayMethod( String name)
    {

        String sql = "Select * from member where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Member(rs.getString("id"),
                    rs.getString("sex"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("address"),
                    rs.getString("date_born"), rs.getString("phone"), rs.getString("marry"), rs.getString("email_address"),
                    rs.getInt("attendance"), rs.getString("login_date"), rs.getString("in_church"),
                    rs.getString("resent")), name);
        }
        catch (EmptyResultDataAccessException ex)
        {
            return null;
        }
    }
    public String getCurrentDate(String id)
    {
        String sql = "Select resent from member where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, numRow) ->
                    new String(rs.getString("resent")), id);
        }
        catch (EmptyResultDataAccessException ex)
        {
            return "No Value Found";
        }
        catch (NullPointerException n)
        {
            return "No Value Found";
        }
    }

/*
     * Method to update the member table information. all information about a particular member in updated at once
     * *//*
*/

    public int updateMemberInfo(String id, String address,String dateBirth,String email,String first,String last,String marry,
                               String phone, String sex, String idlast )
    {
        String sql =  "update member set id =?1, sex =?2,last_name =?3, first_name=?4," +
                "marry =?5,address = ?6, phone = ?7,date_born = ?8, email_address = ?9 WHERE id = ?10";
        return jdbcTemplate.update(sql, id,
                sex,last,first,marry,address,phone,dateBirth,email,idlast);
    }

    public int addMemberInfo(Member member)
    {
        String sql = " INSERT INTO member (id,sex,last_name,first_name,address,date_born,phone,marry,email_address," +
                "attendance,login_date,in_church,resent)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, member.getId(),member.getSex(),member.getLastName(),member.getFirstName()
                ,member.getAddress(),member.getDateBorn(),member.getPhone(),member.getMarry(),member.getEmailAddress(),
                member.getAttendance(),member.getLoginDate(),member.getInChurch(),member.getResent());
    }




    public void findAllMember()
    {
        String sql = "SELECT id, sex,last_name ,first_name ,address,date_born,phone,marry,email_address," +
                "attendance,login_date,in_church ,resent FROM Member";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }


    public void findAllMemberBySex(String sex)
    {
        String sql = " Select * from member where sex = ?";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,sex));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
/*
* Update specific member attendance table by setting it to increment by 1.
* the parameter is the string id of the member.
* */
    public int updateAttendance(String useId)
    {
        String sql = "UPDATE member SET attendance = attendance +1 WHERE id = ?";
        return jdbcTemplate.update(sql,useId);
    }
    public void marriedSelection(String marriedSingle)
    {
        String sql = " Select * from member where marry = ?";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,marriedSingle));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

}




