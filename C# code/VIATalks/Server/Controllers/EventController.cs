using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DataClasses;
using System.Net.NetworkInformation;

namespace Server.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class EventController : ControllerBase
    {
        public List<Event> events;
        public EventController()
        {
            events = new List<Event>
            {
                // Mock data
                new Event(
                "How to be cool",
                "Educational",
                DateTime.Now,
                DateTime.Now.AddHours(2),
                new Event.EventHost("Kenneth", "Jensen", "123@abc.com", "12345678"))
            };
        }

        [HttpGet]
        public async Task<List<Event>> GetEvents()
        {
            // TODO: Query Database
            Console.WriteLine("GetEvents called");
            return events;
        }

        [HttpGet("{eventName}")]
        public async Task<Event> GetEvent(String eventName)
        {
            // TODO: Query Database
            Console.WriteLine("GetEvent called");
            foreach (Event e in events)
            {
                if (e.EventName == eventName)
                    return e;
            }

            return null;
        }

        [HttpPost]
        public async Task<ActionResult<bool>> AddEvent(Event e)
        {
            // TODO: Query Database
            Console.WriteLine("AddEvent called\n");
            events.Add(e);
            Console.WriteLine("Adding event:\n" + e.ToString());

            return true;
        }

    }
}
