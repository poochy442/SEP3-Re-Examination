using DataClasses;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace Client.Model
{
    public class RoomModel
    {
        public Room room { get; set; }
        [Required]
        public int RoomNumber { get; set; }
        [Required]
        public char Block { get; set; }
        [Required]
        public int Capacity { get; set; }
        [Required]
        public double Area { get; set; }
        public Room GetRoom()
        {
            return new Room(-1, RoomNumber, Block, Capacity, Area);
        }
        public void SetRoom(Room r)
        {
            room = r;
            RoomNumber = r.RoomNumber;
            Block = r.Block;
            Capacity = r.Capacity;
            Area = r.Area;
        }
    }
}
