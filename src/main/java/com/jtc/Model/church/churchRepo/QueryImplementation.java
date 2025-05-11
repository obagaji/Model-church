

package com.jtc.Model.church.churchRepo;

import com.jtc.Model.church.churchEntity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Component;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Component

public class QueryImplementation extends AbstractTableModel {

    @Autowired
    private JdbcTemplate jdbcTemplate;
  SqlRowSetMetaData resultSetMetaData;

//  @Autowired
   SqlRowSet resultSet;
    private int numberOfRows;

    public QueryImplementation( )  {
    }

 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setQuery(SqlRowSet query) throws SQLException, IllegalStateException
    {
        resultSetMetaData =  query.getMetaData();

        query.last();

        resultSet = query;

        numberOfRows = query.getRow();

        fireTableStructureChanged();
    }

    public String getColumnName(int column)
    {
        return resultSetMetaData.getColumnName(column + 1);
    }

    public Class getColumnClass(int column) throws IllegalStateException
    {
            try
            {
                String className = resultSetMetaData.getColumnClassName(column + 1);

                return Class.forName(className);
            }
             catch (ClassNotFoundException classNotFoundException)
             {
                classNotFoundException.printStackTrace();
            }

            return Object.class;
    }

    public int getColumnCount() throws IllegalStateException
    {
        int value = 0;

        if (resultSetMetaData!=null)
        {
            value = resultSetMetaData.getColumnCount();
        }

        return value;
    }

    public int getRowCount() throws IllegalStateException
    {

            return numberOfRows;
    }

    public Object getValueAt(int row, int column) throws IllegalStateException
    {
            resultSet.absolute(row + 1);

            return resultSet.getObject(column + 1);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // delete worker login attendance
    private  int deleteWorkerLogin(String id)
    {
        String sql = "Delete from Workers_Login where Workers_Login.id =?";
        return jdbcTemplate.update(sql, id);
    }

    public int deleteNonMember(String id)
    {
        String sql = " Delete from Non_Worker where non_Worker_Id = ?";

        return jdbcTemplate.update(sql,id);
    }
    public int deleteWorker(String id)
    {
        String sql = " Delete from workers where worker_Id = ?";

        return jdbcTemplate.update(sql,id);
    }
    public int deleteSerialMemberWithId(String id)
    {
        String sql = " Delete from Serial_Member where serial_member.id=?  ";

        return jdbcTemplate.update(sql,id);
    }
    public int deleteSerialMember()
    {
        String sql = " Delete from Serial_Member  ";
        deleteFromWorker_Login();

        return jdbcTemplate.update(sql);
    }
    private int deleteFromWorker_Login()
    {
        String sql = " Delete from Workers_Login ";
        return jdbcTemplate.update(sql);
    }
    public int deleteMember(String id)
    {
        String sql = " Delete from Member WHERE id = ?";

        deleteNonMember(id);

        deleteWorker(id);

        deleteDateClass(id);
        deleteSerialMemberWithId(id);
        deleteWorkerLogin(id);
        return 1; //jdbcTemplate.update(sql,id);
    }
    public int deleteDateClass(String memberId)
    {
        String sql = " Delete from date_class WHERE  idmember = ?";

        return jdbcTemplate.update(sql, memberId );
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
        String sql = " SELECT serial_member.id, serial_member.sex, serial_member.last_name, serial_member.first_name, " +
                "serial_member.status ,serial_member.address , serial_member.phone ," +
                " serial_member.attendance ,serial_member.register_date,serial_member.resent,workers.depart_names," +
                "workers.worker_position" +
                " FROM serial_member " +
                "INNER JOIN Workers ON workers.worker_Id = serial_member.id";

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
        String sql = "SELECT id,sex,last_name,first_name,status,address,phone," +
                "  attendance ,register_date,resent FROM serial_member" +
                "  INNER JOIN Non_Worker ON Non_Worker.non_worker_Id =  Serial_Member.id ";
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
    public Member findByFirstNameAndLastName(String first, String last)
    {
        Object[] objects = new Object[2];

        objects[0] = first;

        objects[1] = last;

        String sql = "SELECT * FROM member  WHERE member.first_name = ? "  +
                "UNION" +
                "SELECT * FROM member WHERE  member.last_name = ? ";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Member(rs.getString("id"),
                rs.getString("sex"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("address"),
                rs.getString("date_born"), rs.getString("phone"), rs.getString("status"),
                rs.getInt("attendance"), rs.getString("register_date"),
                rs.getString("resent"), rs.getString("member_photo")),objects);
    }

    public  List<Member> findByFirstName(String first)
    {
        List<Member> listMember;

        String sql = "SELECT * FROM Member  WHERE first_name = ?";

        listMember = jdbcTemplate.query(sql, (rs, rowNum) -> new Member(rs.getString("id"),
                    rs.getString("sex"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("address"),
                    rs.getString("date_born"), rs.getString("phone"), rs.getString("status"),
                    rs.getInt("attendance"), rs.getString("register_date"),
                    rs.getString("resent"), rs.getString("member_photo")), first);

        if (listMember.isEmpty())
        {
            listMember.add(new Member("No Id","","No Name","No Name","","",
                         "","",0,"","",""));//, ""));
        }
        return listMember;
    }
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
// FOR SERIAL MEMBER TABLE
public boolean displaySerialName(String memberId)
{
    String name = "";

    String sql = "SELECT id From serial_member where serial_member.id = ?";

    try {

        name = jdbcTemplate.queryForObject(sql,(rs, rowNum) -> rs.getString("id")
                ,memberId);

    }
    catch(EmptyResultDataAccessException ex)
    {
        return false;
    }

    if (name!=null) {
        if (name.equalsIgnoreCase(memberId))
            return true;

        else
            return false;
    }
    else return false;


}
// FOR NON SERIAL TABLE



    public boolean displayName(String memberId)
    {
        String name = "";

        String sql = "SELECT id From member where member.id = ?";

        try {

             name = jdbcTemplate.queryForObject(sql,(rs, rowNum) -> rs.getString("id")
                    ,memberId);

        }
        catch(EmptyResultDataAccessException ex)
        {
            return false;
        }

        if (name!=null) {
            if (name.equalsIgnoreCase(memberId))
                return true;

            else
                return false;
        }
        else return false;


    }
/*
     * Use the naitive Query to update the member table. the inchurch
     * property will be update to reflect the present day. to show if the user was in church the last time
     * */

    public int updateAttendanceOfMembers( String id)
    {
        String sql = " update date_class set out_church  = 'PRESENT' where idmember = ?";

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

        try
        {
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
        String sql = "Select * from non_worker where non_worker_id = ?";

        try {
            return jdbcTemplate.query(sql, (rs, numRow) -> new NonWorker(rs.getString("non_Worker_Id"),
                    rs.getString("join_date")),nonWorkerId).stream().findFirst().orElseThrow();
        }
        catch (NoSuchElementException ex)
        {
            return new NonWorker("LE000","00-00-00");
        }
    }
    public DateClass findDateExist(String dateString)
    {
        String sql = "SELECT * FROM date_class WHERE class_date = ?";

        return jdbcTemplate.query(sql,(r,s)-> new DateClass(r.getString("id_member"),
                r.getString("class_data"),r.getString("out_Church")),dateString)
                .stream().findFirst().orElseThrow();
    }

    public void getBirthDaysWorker()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String formatedDate = dateFormat.format(new Date());

        String searchString = formatedDate.substring(0,5);

        String sql = "SELECT last_name,first_name, status, address,phone,date_born FROM member " +
                "INNER JOIN workers ON workers.worker_Id = member.id AND member.date_born = ? ";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,searchString));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

    }


/*
* This method will be called once a member login. The parameters are the  new date and the id login
* returns an integer to signifys success.
* */
    public int updateCurrentDateInfo(String member, String id)
    {
        String sql = "update serial_member set resent = ? where id = ? ";

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
    public int updateInchurchValue( String name)
    {
        String sql = "UPDATE date_class SET out_church = 'PRESENT'  WHERE idmember = ?";
        return jdbcTemplate.update(sql,name);
    }

    public int addDateClass(DateClass dateClass)
    {
        String sql = " INSERT INTO date_class ( idmember, class_date,  out_church)VALUES(?,?,?)";
        return jdbcTemplate.update(sql, dateClass.getIdmember(),dateClass.getClassDate(),dateClass.getOutChurch());
    }
    public int updateAllDateClass(String id, String inOrOut)
    {
        String sql = "UPDATE date_class SET out_church = ?  WHERE idmember = ?";
        return jdbcTemplate.update(sql,inOrOut,id);
    }
    public List<DateClass>getAllDateClass()
    {

        String sql = "SELECT idmember, class_date, out_church FROM date_class";

        return  jdbcTemplate.query(sql,(rs, rowNum) -> new DateClass(
                rs.getString("idmember"),rs.getString("class_date"),
                rs.getString("out_church")));

    }

    public void selectOutSunday(String notPresent)
    {
        String sql = "SELECT Serial_Member.serial_number,Serial_Member.id, Serial_Member.sex,Serial_Member.last_name ,Serial_Member.first_name " +
                ",Serial_Member.address,Serial_Member.phone,Serial_Member.status," +
                "Serial_Member.attendance ,Serial_Member.resent FROM Serial_Member " +
                "INNER JOIN date_class ON Serial_Member.id = date_class.idmember WHERE out_church = ?";
        String value = notPresent.toUpperCase();
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



    public List<SerialMember> findByIdValue(String id)
    {
        boolean notTrue = false;
        List<SerialMember> memberList = new ArrayList<>();
        List<SerialMember>emptyList = new ArrayList<>();
        String sql = "SELECT * FROM serial_member WHERE serial_member.id = ?";
     //   try {

             memberList= jdbcTemplate.query(sql,(rs, rowNum) -> new SerialMember(rs.getInt("serial_number"),rs.getString("id" ),
                    rs.getString("sex"),rs.getString("last_name"),
                    rs.getString("first_name"),rs.getString("address"),rs.getString("date_born"),
                    rs.getString("phone"),  rs.getString("status"),
                    rs.getInt("attendance"), rs.getString("register_date"),
                    rs.getString("resent"),rs.getString("member_photo")),id);
             if (memberList.isEmpty())
             {
                 memberList.add(new SerialMember(0,"No Id","","No Name","No Name","","",
                         "","",0,"","",""));//, ""));
             }

        return memberList;
    }

    public String searchAttendanceById(String id)
    {
        String value = "";
        if(!id.equalsIgnoreCase("")|| id!=null)
        {
            String sql = "Select attendance from member where id = ?";
            try {
                value = jdbcTemplate.queryForObject(sql, (rs, numRow) ->
                        new String(rs.getString("attendance")),id);
            } catch (EmptyResultDataAccessException ex) {
                return value;
            }
        }
        return value;
    }
    public SerialMember displayMethod(String name)
    {
        SerialMember newMember;
        String sql = "SELECT * FROM serial_member WHERE id = ?";
        try {
            newMember = jdbcTemplate.queryForObject(sql,(rs, s)->new SerialMember(rs.getInt("serial_number"),rs.getString("id"),rs.getString("sex")
            ,rs.getString("last_name"),rs.getString("first_name"),rs.getString("address"),
                    rs.getString("date_born"),rs.getString("phone"),rs.getString("status")
            ,rs.getInt("attendance"),rs.getString("register_date"),rs.getString("resent")
              , rs.getString("member_photo")),name);
        }
        catch (EmptyResultDataAccessException ex)
        {
            return null;
        }
        return newMember;
    }
    // method to get member from member table
    public Member displayMemberMethod(String name)
    {
        Member newMember;
        String sql = "SELECT * FROM member WHERE id = ?";
        try {
            newMember = jdbcTemplate.queryForObject(sql,(rs, s)->new Member(rs.getString("id"),rs.getString("sex")
                    ,rs.getString("last_name"),rs.getString("first_name"),rs.getString("address"),
                    rs.getString("date_born"),rs.getString("phone"),rs.getString("status")
                    ,rs.getInt("attendance"),rs.getString("register_date"),rs.getString("resent")
                    , rs.getString("member_photo")),name);
        }
        catch (EmptyResultDataAccessException ex)
        {
            return null;
        }
        return newMember;
    }

    public String getCurrentDate(String id)
    {
        String sql = "Select resent from serial_member where id = ?";
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

    public int updateMemberInfo(String id, String address,String dateBirth,String first,String last,String status,
                               String phone, String sex,
                                String memberPhoto,
             String idlast )
    {
        String sql =  "update member set id =?1, sex =?2,last_name =?3, first_name=?4," +
                "status =?5,address = ?6, phone = ?7,date_born = ?8,  member_photo = ?9 WHERE id = ?10";
        return jdbcTemplate.update(sql, id,
                sex,last,first,status,address,phone,dateBirth,
                memberPhoto,
                idlast);
    }
    // updating new member table info
    public int updateSerialMemberInfo(String id, String address,String dateBirth,String first,String last,String status,
                                String phone, String sex,
                                String memberPhoto,
                                String idlast )
    {
        String sql =  "update serial_member set id =?1, sex =?2,last_name =?3, first_name=?4," +
                "status =?5,address = ?6, phone = ?7,date_born = ?8,  member_photo = ?9 WHERE id = ?10";
        return jdbcTemplate.update(sql, id,
                sex,last,first,status,address,phone,dateBirth,
                memberPhoto,
                idlast);
    }

    private int getMax()
    {
       String sql= "SELECT COUNT(*) + 1 FROM serial_member";
       return jdbcTemplate.queryForObject(sql, Integer.class);
    }



    // create a new table where you add a new column to the existing table for memebers
    public int newAddMember(Member member)  {
        String sql = " INSERT INTO Serial_Member (serial_number,id,sex,last_name,first_name,address,date_born,phone,status," +
                "attendance,register_date,resent" +
                ",member_photo" +
                ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?" +
                ",?" +
                ")";
        int value = getMax();
        return jdbcTemplate.update(sql,value, member.getId(),member.getSex(),member.getLastName(),member.getFirstName()
                ,member.getAddress(),member.getDateBorn(),member.getPhone(),member.getStatus(),
                member.getAttendance(),member.getRegisterDate(),member.getResent(),member.getMemberPhoto());
    }


    public int addMemberInfo(Member member)
    {
        String sql = " INSERT INTO member (id,sex,last_name,first_name,address,date_born,phone,status," +
                "attendance,register_date,resent" +
                ",member_photo" +
                ")VALUES(?,?,?,?,?,?,?,?,?,?,?" +
                ",?" +
                ")";
        return jdbcTemplate.update(sql, member.getId(),member.getSex(),member.getLastName(),member.getFirstName()
                ,member.getAddress(),member.getDateBorn(),member.getPhone(),member.getStatus(),
                member.getAttendance(),member.getRegisterDate(),member.getResent(),member.getMemberPhoto());
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
        String sql = "UPDATE serial_member SET attendance = attendance +1 WHERE id = ?";
        return jdbcTemplate.update(sql,useId);
    }
    public int updateAttendanceDate(String useId, String newdate)
    {
        String sql = "UPDATE serial_member SET resent = ?  WHERE id = ?";
        return jdbcTemplate.update(sql,useId,newdate);
    }
    public void marriedSelection(String marriedSingle)
    {
        String sql = " Select member.last_name, member.first_name, member.status ,member.address , member.phone , +" +
                " member.register_date from member where status = ?";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql,marriedSingle));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void MemberSelection()
    {
        String sql = " Select serial_Member.serial_number, serial_Member.last_name, serial_Member.first_name, serial_Member.status ,serial_Member.address , serial_Member.phone , +" +
                " serial_Member.register_date,serial_Member.resent from serial_Member";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void OldMemberSelection()
    {
        String sql = " Select  last_name, first_name, status ,address , phone , +" +
                " register_date,resent from Member";
        try {
            setQuery( jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }


    public void memberAttendanceTime(String searchDate)
    {
        String sql = "select first_name,last_name,phone,address from serial_member where resent = ?";
        try{
            setQuery(jdbcTemplate.queryForRowSet(sql,searchDate));
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }

    }
    public int updateWorkerAttendance(String loginTime,String useId)
    {
        String sql = "UPDATE Workers_Login SET login_Time = ? WHERE id = ?";
        return jdbcTemplate.update(sql,loginTime,useId);
    }
    // method to update the status of the member for login time purpose
    public int updateLoginMemberStatus(String loginStatus,String useId)
    {
        String sql = "UPDATE Workers_Login SET member_status = ? WHERE id = ?";
        return jdbcTemplate.update(sql,loginStatus,useId);
    }
//INSERT INTO THE LOGIN TIME TABLE AS EVERY MEMBER IS CREATED.
    public int addWorkerAttendance(WorkersLogin workersAttendance)
    {
        String sql = " INSERT INTO Workers_Login ( id,login_Time,member_status,login_Date) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql, workersAttendance.getWorkerTimeId(),workersAttendance.getLoginTime(),
                workersAttendance.getMemberStatus(),workersAttendance.getLoginDate());
    }


    //get all the login time
    public void getAllLoginTime()
    {
        String sql = " SELECT serial_member.last_name, serial_member.first_name, Workers_Login.login_Time,Workers_Login.login_Date FROM serial_member " +
                "INNER JOIN Workers_Login ON Workers_Login.id = serial_member.id ";
        try {
            setQuery(jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    // get the information about  a member from the worker login table
    public String getAttendanceIdMember(String id)
    {
        String sql = " Select member_status from Workers_Login where id=? ";
        return jdbcTemplate.queryForObject(sql,String.class,id);

    }
    // GET ALL THE WORKERS LOGIN TIME
    public void getAllWorkersLoginTime()
    {
        String sql = " SELECT serial_member.last_name, serial_member.first_name, serial_member.status ,serial_member.address , serial_member.phone ," +
                " serial_member.attendance ,serial_member.register_date,serial_member.resent," +
                "Workers_Login.login_Time" +
                " FROM serial_member " +
                "INNER JOIN Workers_Login ON Workers_Login.id = serial_member.id WHERE Workers_Login.member_status = 'Worker'";
        try {
            setQuery(jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
// GET ALL THE ATTENDANCE OF ALL MEMBERS REGISTERED IN THE DATA BASE, NOT ONLY WORKERS
    public void getAllMembersAttendanceTime()
    {
        String sql = " SELECT serial_member.last_name, serial_member.first_name, serial_member.status ,serial_member.address , serial_member.phone ," +
                "serial_member.attendance ,serial_member.register_date,serial_member.resent," +
                "Workers_Login.login_Time" +
                " FROM serial_member " +
                "INNER JOIN Workers_Login ON Workers_Login.id = serial_member.id WHERE Workers_Login.member_status = 'NONWORKER'";
        try {
            setQuery(jdbcTemplate.queryForRowSet(sql));
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public int setAllMemberToAbsent(String nowDate)
    {
        String sql = " UPDATE date_class set out_church = 'ABSENT'  && class_date = ? ";
       return  jdbcTemplate.update(sql, nowDate);
    }
//
public List<Member> findAllMemberWithoutSerial()
{
    String sql = "SELECT   member_photo, id, sex,last_name ,first_name ,address,date_born,phone,status," +
            "attendance,register_date,resent FROM member";

    List<Member> memberList= jdbcTemplate.query(sql,(rs, rowNum) -> new Member(rs.getString("id" ),
            rs.getString("sex"),rs.getString("last_name"),
            rs.getString("first_name"),rs.getString("address"),rs.getString("date_born"),
            rs.getString("phone"),  rs.getString("status"),
            rs.getInt("attendance"), rs.getString("register_date"),
            rs.getString("resent"), rs.getString("member_photo")));

    return memberList;
}


    public List<SerialMember> findAllMember()
    {
        String sql = "SELECT serial_number,  member_photo, id, sex,last_name ,first_name ,address,date_born,phone,status," +
                "attendance,register_date,resent FROM Serial_Member";

        List<SerialMember> memberList= jdbcTemplate.query(sql,(rs, rowNum) -> new SerialMember(rs.getInt("serial_number"),rs.getString("id" ),
                rs.getString("sex"),rs.getString("last_name"),
                rs.getString("first_name"),rs.getString("address"),rs.getString("date_born"),
                rs.getString("phone"),  rs.getString("status"),
                rs.getInt("attendance"), rs.getString("register_date"),
                rs.getString("resent"), rs.getString("member_photo")));

        return memberList;
    }
    public List<Member> getBirthDaysWorkerList()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String formatedDate = dateFormat.format(new Date());
        String searchString = formatedDate.substring(3,5);
        String sql = "SELECT * FROM serial_member ";

        List<Member> memberList= jdbcTemplate.query(sql,(rs, rowNum) -> new Member(rs.getString("id" ),
                rs.getString("sex"),rs.getString("last_name"),
                rs.getString("first_name"),rs.getString("address"),rs.getString("date_born"),
                rs.getString("phone"),  rs.getString("status"),
                rs.getInt("attendance"), rs.getString("register_date"),
                rs.getString("resent"), rs.getString("member_photo")));
        return memberList.stream().filter(member ->
                (member.getDateBorn().substring(3,5).equalsIgnoreCase(searchString))).toList();

    }



}




