using System;

namespace DataClasses
{
    public class Room
    {
        public String Name { get; set; }    // Room name
        public int Capacity { get; set; }   // Room capacity
        public int Attending { get; set; }  // Currently attending users
        public bool IsFull { get; set; }    // Whether the Room is full

        public bool AddUser()
        {
            if(IsFull) // Check if room is full
                return false;

            Attending++;

            if(Attending >= Capacity) // Check if the room is full
                IsFull = true;

            return true;
        }

        public bool RemoveUser()
        {
            if (Attending <= 0) // Check if room is already empty
                return false;

            Attending--;

            if (IsFull == true) // Check if room is full
                IsFull = false;

            return true;
        }
    }
}
