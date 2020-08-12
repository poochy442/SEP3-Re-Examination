using System;
using System.Collections.Generic;
using System.Text;

namespace Shared
{
    [Serializable]
    class Ticket
    {
        public int Id { get; set; }
        public string TicketNumber { get; set; }

        public Ticket(int id, string ticketNumber)
        {
            Id = id;
            TicketNumber = ticketNumber;
        }
    }
}
