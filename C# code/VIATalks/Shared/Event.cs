using System;
using System.Collections.Generic;
using System.Text;

namespace DataClasses
{
    [Serializable]
    public class Event
    {
        [Serializable]
        public struct EventHost
        {
            public string FirstName { get; set; }
            public string LastName { get; set; }
            public string Email { get; set; }
            public string Telephone { get; set; }
            public EventHost(String firstName, string lastName, string email, string telephone)
            {
                FirstName = firstName;
                LastName = lastName;
                Email = email;
                Telephone = telephone;
            }
        }
        public int Id { get; set; }
        public String Category { get; set; }
        public String EventName { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
        public int NumberOfSeats { get; set; }
        public int RegisteredUsers { get; set; }
        public EventHost Host { get; set; }
        public Room Room { get; set; }
        public Campus Campus { get; set; }
        public enum CATEGORIES
        {
            Educational,
            Entertainment,

        }
        public Event()
        {

        }
        public Event(
            int id,
            String eventName,
            String category,
            DateTime startTime,
            DateTime endTime,
            EventHost host,
            Room room,
            Campus campus)
        {
            Id = id;
            Category = category;
            EventName = eventName;
            StartTime = startTime;
            EndTime = endTime;
            Host = host;
            Room = room;
            Campus = campus;
        }

        public override string ToString()
        {
            return "Event Name: " + EventName +
                "\nCategory: " + Category +
                "\nStart Time: " + StartTime +
                "\nEnd Time: " + EndTime +
                "\nNumber Of Seats: " + NumberOfSeats +
                "\nRegistered Users: " + RegisteredUsers +
                "\nHost: " + Host.FirstName + " " + Host.LastName;
        }
    }
}
