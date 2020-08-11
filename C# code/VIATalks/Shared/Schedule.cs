using System;
using System.Collections.Generic;
using System.Text;

namespace Shared
{
    [Serializable]
    class Schedule
    {
        public int Id { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }

        public Schedule(int id, DateTime startDate, DateTime endDate)
        {
            Id = id;
            StartDate = startDate;
            EndDate = endDate;
        }
    }
}
