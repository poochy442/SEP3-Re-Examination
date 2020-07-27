using System;
using System.Collections.Generic;
using System.Text;

namespace DataClasses
{
    public class Event
    {
        public String Category { get; set; }
        public String Topic { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
        public int NumberOfSeats { get; set; }
        public int RegisteredUsers { get; set; }

        public Event(
            String topic,
            String category,
            DateTime startTime,
            DateTime endTime)
        {
            Category = category;
            Topic = topic;
            StartTime = startTime;
            EndTime = endTime;
        }
    }
}
