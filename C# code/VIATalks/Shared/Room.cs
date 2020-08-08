using System;
using System.Collections.Generic;
using System.Text;

namespace DataClasses
{
    [Serializable]
    public class Room
    {
        public int Id { get; set; }
        public int RoomNumber { get; set; }
        public char Block { get; set; }
        public int Capacity { get; set; }
        public double Area { get; set; }
        public Room(
            int id,
            int roomNumber,
            char block,
            int capacity,
            double area)
        {
            Id = id;
            RoomNumber = roomNumber;
            Block = block;
            Capacity = capacity;
            Area = area;
        }
    }
}
