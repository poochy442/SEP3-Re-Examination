using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DataClasses;
using Microsoft.AspNetCore.Mvc;

namespace Server.Adapter
{
    public class EventAdapter
    {
        public List<Event> events;
        public EventAdapter()
        {
            events = new List<Event>
            {
                // Mock data
                new Event(
                    1,
                    "How to be cool",
                    "Educational",
                    DateTime.Now,
                    DateTime.Now.AddHours(2),
                    new Event.EventHost("Kenneth", "Jensen", "123@abc.com", "12345678"))
            };
            events[0].NumberOfSeats = 50;
        }

        public async Task<List<Event>> GetEvents()
        {
            // TODO: Query Database
            return events;
        }

        public async Task<Event> GetEvent(int id)
        {
            // TODO: Query Database
            foreach (Event e in events)
            {
                if (e.Id == id)
                    return e;
            }

            return null;
        }

        public async Task<ActionResult<bool>> AddEvent(Event e)
        {
            // TODO: Query Database
            events.Add(e);

            return true;
        }

        public async Task<ActionResult<bool>> EditEvent(int id, Event e)
        {
            // TODO: Query Database
            for (int i = 0; i < events.Count; i++)
            {
                if (events[i].Id == id)
                {
                    events[i] = e;
                    return true;
                }
            }

            return false;
        }

        public async Task<ActionResult<bool>> CancelEvent(int id)
        {
            for (int i = 0; i < events.Count; i++)
            {
                if (events[i].Id == id)
                {
                    events.RemoveAt(i);
                    return true;
                }
            }
            return false;
        }
    }
}
