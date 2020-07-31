using DataClasses;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Client.Models
{
    public class EventModel
    {
        public Event Event { get; set; }
        [Required]
        [EnumDataType(typeof(Event.CATEGORIES))]
        public String Category { get; set; }
        [Required]
        [StringLength(50, ErrorMessage = "Name is too long.")]
        public string EventName { get; set; }
        [Required]
        public DateTime StartTime { get; set; }
        [Required]
        public DateTime EndTime { get; set; }
        [Required]
        public int NumberOfSeats { get; set; }
        [DefaultValue(0)]
        public int RegisteredUsers { get; set; }
        // [Required]
        public Event.EventHost Host { get; set; }

        public Event GetEvent()
        {
            return new Event(EventName, Category, StartTime, EndTime, Host);
        }
    }
}
