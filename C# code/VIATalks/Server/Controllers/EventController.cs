using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DataClasses;

namespace Server.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class EventController : ControllerBase
    {
        public List<Event> events;

        public EventController()
        {
            events = new List<Event>();
            // Mock data
            events.Add(new Event(
                "How to be cool",
                "Educational",
                DateTime.Now,
                DateTime.Now.AddHours(2)));
        }

        [HttpGet]
        public async Task<List<Event>> GetEvents()
        {
            // TODO: Query Database
            return events;
        }

        [HttpGet("{topic}")]
        public async Task<Event> GetEvent(String topic)
        {
            // TODO: Query Database
            foreach (Event e in events)
            {
                if (e.Topic == topic)
                    return e;
            }

            return null;
        }

        [HttpPost]
        public async Task<ActionResult<Room>> AddEvent(Event e)
        {
            // TODO: Query Database
            events.Add(e);

            return CreatedAtAction(nameof(GetEvent), new { topic = e.Topic }, e);
        }

    }
}
